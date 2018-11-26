package cn.base.resource.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.base.tool.ToolString;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.Model;
import cn.dreampie.orm.TableMeta;
import cn.dreampie.orm.dialect.Dialect;

/**
 * Model基础类
 * 
 * @author sunzc
 * @param <M>
 */
public abstract class RestyBaseModel<M extends Model<M>> extends Model<M> {
	private static Logger LOG = Logger.getLogger(RestyBaseModel.class);
	private static final long serialVersionUID = -900378319414539856L;

	public static final String SERVER_DB_OP_FAIL = "服务器忙，本次提交不成功";
	public static final String TIPS_NO_PAGER = "缺少分页信息";
	
	public String[] EXT_CLOS = new String [0];
	
	/**
	 * 服务器最大分页一页数据
	 */
	public static final int MAX_PAGER_PER_COUNT = 15;
	
	/**
	 * 数据状态 SQL_STATUS 固定列名
	 */
	public static final String SQL_STATUS = "sql_status";
	/**
	 * 以下为SQL固定列，用于固定业务逻辑执行
	 */
	public static final String CREATE_UUID = "create_uuid";
	public static final String UPDATE_UUID = "update_uuid";
	public static final String DISABLE_UUID = "disable_uuid";
	public static final String DEL_UUID = "del_uuid";
	public static final String CREATE_AT = "create_at";
	public static final String UPDATE_AT = "update_at";
	public static final String DISABLE_AT = "disable_at";
	public static final String DEL_AT = "del_at";
	public static final String STATE = "state";
	public static final String VERSION = "version";
	public static final String UUID = "uuid";
	
	
	/**
	 * 搜索条件
	 */
	protected static final String QUERY_NAME = "query";
	
	/**
	 * 替代 IDS 在新版本基线使用
	 */
	public static final String ID = "id";
	
	/**
	 * 状态
	 */
	protected static final String STATUS = "status";
	
	public static final String SQL_ID_SPLIT = ",";
	
	public static final String SQL_KEY_SPLIT = "=";
	
	public static final String SQL_AND = " AND ";
	public static final String SQL_OR = " OR ";
	public static final String SQL_PARAM = "? ";
	public static final String SQL_PARAM_EQ = "=? ";
	
	public static final String SQL_KEY_WORDS_SPLIT = "#";
	
	public static final String STRING_FORMAT = "%s";
	
	
	/**
	 * SQL ID 合并符 使用时需要确认此为字符串唯一合并符
	 */
	public static final String SQL_ID_MERGE = "|";

	/**
	 * SQL 字符串 ID 格式化
	 */
	public static final String SQL_STR_ID_FORMAT = "'%s'";
	
	/**
	 * 处理空字符串
	 */
	public static final String EMPTY_STRING_FORMAT = "%s ";

	

	/**
	 * SQL 排序字符串
	 */
	public static final String SQL_ORDER_BY = " order by ";
	public static final String SQL_ORDER_ASC = " ASC ";
	public static final String SQL_ORDER_DESC = " DESC ";

	public static final int PAGE_SPILT_MAX_SIZE = 15;

	public static final String  local_split = "区";

	/**
	 * 一个月毫秒数
	 * 
	 */
	public final static long ONE_MONTH_SECMILLON = 1000L * 60L * 60L * 24L * 31L;
	
	/**
	 * 一天的毫秒
	 */
	public final static long ONE_DAY_SECMILLON = 1000L * 60L * 60L * 24L ;
	
	/**
	 * sql 总数
	 */
	public static final String COUNT_SQL = "select count(*) as counts from  ( %s ) t   ";
	
	/**
	 * LONG 主键
	 * 确认数据库存在主键
	 * @return
	 */
	public long getLIds() {
		return getLong(getTableMeta().getGeneratedKey());
	}
	
	
	/**
	 * 防止脏数据   update方法
	 */
	public boolean updateForVersion() {
		TableMeta table = getTableMeta();
		boolean hasVersion = table.hasColumn("version");
		
		if(hasVersion){// 是否需要乐观锁控制，表是否有version字段
			String name = table.getTableName();
			String pk = table.getGeneratedKey();
			
			// 1.数据是否还存在
			Model<M> modelOld = findFirst("select version from "+name+" where "+pk+" = ?" , get(pk));
			if(null == modelOld){ // 数据已经被删除
				throw new RuntimeException("数据库中此数据不存在，可能数据已经被删除，请刷新数据后在操作");
			}
			
			// 2.乐观锁控制
			Long versionDB = modelOld.get("version"); // 数据库中的版本号
			Long versionForm = Long.parseLong(get("version")+"") + 1; // 表单中的版本号
			if(!(versionForm > versionDB)){
				throw new RuntimeException("表单数据版本号和数据库数据版本号不一致，可能数据已经被其他人修改，请重新编辑");
			}
		}
		return super.update();
	}
	
	/**
	 * 获取主键名称
	 * @return 主键名称
	 */
	public String getPk() {
		return UUID;
	}

	
	/**
	 * 获取主键值：非复合主键
	 * @return
	 */
	public String getPKValue(){
		return this.get(UUID)+"";
	}
	
	public long findCount(String sql,Object... params){
		Model<M> m = findFirst(sql, params);
		if (m!=null){
			long counts = m.get("counts");
			return counts;
		}else{
			return 0;
		}
	}
	public M findByUuid(String uuid){
		 TableMeta tableMeta = getTableMeta();
	    Dialect dialect = getDialect();
	    String sql = dialect.select(tableMeta.getTableName(), "", UUID + "=?");
	    return findFirst(sql,uuid);
	}

	/**
	 * 安全取值
	 * @param name
	 * @return
	 */
	public String getStr(String name){
		Object value = this.get(name);
		if(null !=  value){
			// 解决强转出错
			return String.valueOf(value);
		}
		else return "";
	}
	
	/**
	 * 安全取值
	 * 只转换正数
	 * 
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		String value = this.getStr(name);
		if (ToolString.isNumeric(value)) {
			return Long.parseLong(value);
		}else {
			return 0;
		}
	}
	
	/**
	 * 安全取值
	 * 只转换正数
	 * 只取前10位
	 * 
	 * @param name
	 * @return
	 */
	public int getInt(String name) {
		String value = this.getStr(name);
		if (ToolString.isNumeric(value)) {
			return Integer.parseInt(value);
		} else {
			return 0;
		}
	}
	
	/**
	 * 安全取值
	 * 只转换正数
	 * @param name
	 * @return
	 */
	public Double getDouble(String name) {
		String value = this.getStr(name);
		if (ToolString.isNumericDouble(value)) {
			return Double.parseDouble(value);
		} else {
			return 0.00;
		}
	}
	
	public String getUpdateCols(String... cols){
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String col : cols) {
			if (index==0) {
				sb.append(col);
			} else {
				sb.append(SQL_ID_SPLIT);
				sb.append(col);
			}
			index++;
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成时间间隔查询SQL
	 * @param colName
	 * @return String 时间间隔查询
	 */
	public String betweenTimeSql(String colName){
		StringBuilder where = new StringBuilder();
		where.append(" ");
		where.append(colName);
		where.append(">=");
		where.append(SQL_PARAM);
		where.append(SQL_AND);
		where.append(colName);
		where.append("<=");
		where.append(SQL_PARAM);
		where.append(" ");
		return where.toString();
	}
	
	/**
	 * 覆盖框架  delete 方法(更新 status=2 ) ：该对象应该要有  status  字段 和主键字段名为id
	 */
	public  boolean delete(){
		StringBuilder where = new StringBuilder(getPk());
		where.append(SQL_KEY_SPLIT);
		where.append(getPKValue());
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}
	/**
	 * 覆盖框架  deleteById 方法(更新 status=2 ) ：该对象应该要有  status  字段  和主键字段名为id
	 */
	public boolean deleteById(Object id){
		StringBuilder where = new StringBuilder(ID);
		where.append(SQL_KEY_SPLIT);
		where.append(id);
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}
	public boolean deleteByUUID(String uuid){
		StringBuilder where = new StringBuilder(UUID);
		where.append(SQL_KEY_SPLIT);
		where.append("'"+uuid+"'");
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}
	
	/**
	 * 通过uuid1,uuid2  字符串批量删除
	 */
	public boolean deleteInUuids(String uuids){
		StringBuilder where = new StringBuilder(UUID);
		where.append(" in ( "+toSql(uuids)+")");
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}
	
	
	/**
	 * 1,2,3    根据id 获取   id,model   的MAP
	 * @param ids
	 * @return
	 */
	public Map<Long,M> mapById(String ids){
		List<M>  list = findInIds((Object[])ids.split(SQL_ID_SPLIT));
		Map<Long,M>  retnMap = new HashMap<Long, M>();
		for (M m : list) {
			Object status = m.get(SQL_STATUS);
			if(status!=null && (Integer)status != 1){
				continue;
			}
			retnMap.put(Long.valueOf(m.get(ID).toString()), m);
		}
		return retnMap;
	}
	
	/**
	 * 对象的List 转换成  key为iid 的  Map  对象
	 * @param list
	 * @return
	 */
	public Map<Long,M> list2MapById(List<M> list){
		Map<Long,M>  retnMap = new HashMap<Long, M>();
		for (M m : list) {
			retnMap.put(Long.valueOf(m.get(ID).toString()), m);
		}
		return retnMap;
	}
	/**
	 * 对象的List 转换成  key为uuid 的  Map  对象
	 * @param list
	 * @return
	 */
	public Map<String,M> list2MapByUUID(List<M> list){
		Map<String,M>  retnMap = new HashMap<String, M>();
		for (M m : list) {
			retnMap.put(m.get(UUID).toString(), m);
		}
		return retnMap;
	}
	
	/**
	 * 对象的List 转换成  key为c传入的  Map  对象
	 * @param list
	 * @return
	 */
	public Map<String,M> list2MapByCol(List<M> list,String col){
		Map<String,M>  retnMap = new HashMap<String, M>();
		for (M m : list) {
			retnMap.put(m.get(col).toString(), m);
		}
		return retnMap;
	}

	/**
	 * 遍历List   获取字段  colunm 的逗号字符串
	 * @param list
	 * @param colunm
	 * @return
	 */
	public String list2Str(List<M> list,String colunm){
		StringBuilder retn = new StringBuilder();
		for (M m : list) {
			String v = m.get(colunm)+"";
			if(ToolString.isNull(v)){
				continue;
			}
			if(!ToolString.isNull(retn)){
				retn.append(SQL_ID_SPLIT);
			}
			retn.append(v);
		}
		return retn.toString();
	}
	
	
	/**
	 * 构建唯一ID
	 * 
	 * @param ids
	 * @return
	 */
	public String toUnionId(Object... ids) {
		StringBuilder sb = new StringBuilder();

		for (Object id : ids) {
			sb.append(id).append(SQL_ID_MERGE);
		}

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString().toLowerCase();
	}

	/**
	 * 把11,22,33...转成'11','22','33'...
	 * 
	 * @param ids
	 * @return
	 */
	public String toSql(String ids) {
		if (null == ids || ids.trim().isEmpty()) {
			return null;
		}

		String[] idsArr = ids.split(SQL_ID_SPLIT);
		StringBuilder sqlSb = new StringBuilder();
		int length = idsArr.length;
		for (int i = 0, size = length - 1; i < size; i++) {
			if (!ToolString.isNull(idsArr[i]))
				sqlSb.append(String.format(SQL_STR_ID_FORMAT, idsArr[i])).append(SQL_ID_SPLIT);
		}
		if (length != 0) {
			sqlSb.append(String.format(SQL_STR_ID_FORMAT, idsArr[length - 1]));
		}

		return sqlSb.toString();
	}

	/**
	 * 把11,22,33...转成'11','22','33'...
	 * 
	 * @param ids
	 * @return
	 */
	public String toSql(String ids, String split) {
		if (null == ids || ids.trim().isEmpty()) {
			return null;
		}

		String[] idsArr = ids.split(split);
		StringBuilder sqlSb = new StringBuilder();
		int length = idsArr.length;
		for (int i = 0, size = length - 1; i < size; i++) {
			if (null != idsArr[i] && !"".equals(idsArr[i].trim()))
				sqlSb.append(" '").append(idsArr[i]).append("', ");
		}
		if (length != 0) {
			sqlSb.append(" '").append(idsArr[length - 1]).append("' ");
		}

		return sqlSb.toString();
	}

	/**
	 * 把数组转成'11','22','33'...
	 * 
	 * @param ids
	 * @return
	 */
	public String toSql(String[] idsArr) {
		if (idsArr == null || idsArr.length == 0) {
			return null;
		}

		StringBuilder sqlSb = new StringBuilder();
		int length = idsArr.length;
		for (int i = 0, size = length - 1; i < size; i++) {
			sqlSb.append(" '").append(idsArr[i]).append("', ");
		}
		if (length != 0) {
			sqlSb.append(" '").append(idsArr[length - 1]).append("' ");
		}

		return sqlSb.toString();
	}

	public String toSql(long[] idsArr) {
		if (idsArr == null || idsArr.length == 0) {
			return null;
		}

		StringBuilder sqlSb = new StringBuilder();

		for (long id : idsArr) {
			if (sqlSb.length() > 0) {
				sqlSb.append(SQL_ID_SPLIT);
			}
			sqlSb.append(id);
		}
		return sqlSb.toString();
	}

	/**
	 *   根据需要IN查询的字段  进行where 查询
	 * @param where   a=?
	 * @param inCol  col_in
	 * @param InValues  1,2,3
	 * @param params   a 的值
	 * @return
	 */
	public List<M> findInByCol(String where ,String inCol,String InValues,Object... params){
		if(!ToolString.isNull(where)) {
			where += " and ";
		}
		List<M> list =  findBy(where+"  "+inCol+ " in ("+toSql(InValues.toString())+") ", params);
		return list;
	}
	
	/**
	 * 行级：过滤
	 * 
	 * @return
	 */
	protected void rowFilter(StringBuilder formSqlSb) {

	}
	
	
	/**
	 * like sql 拼接      col  like '%parm%'
	 * @param where
	 * @param colum
	 * @param parm
	 */
	public void likeSql(StringBuilder where,String colum,String parm){
		if(ToolString.isNull(parm)) {
			return;
		}
		if(where.indexOf("where")!=-1 ){
			where.append("   and  ");
		}else {
			where.append("   where   ");
		}
		where.append(colum);
		where.append("   like ");
		where.append("  concat('%','");
		where.append(parm);
		where.append("','%')  ");
	}
	
	public String likeSql(String where,String colum,String parm){
		StringBuilder sql = new StringBuilder(where);
		likeSql(sql, colum, parm);
		return sql.toString() ;
	}
	
	/**
	 * 从List 集合对象中 获取col  字段值的带逗号的字符串
	 * @param list
	 * @param col
	 * @return
	 */
	public String appendCol(Collection<M> list, String col) {
		StringBuilder cols = new StringBuilder();
		for (M m : list) {
			if(m.get(col) != null){
				if (!ToolString.isNull(cols)) {
					cols.append(SQL_ID_SPLIT);
				}
				cols.append(m.get(col) + "");
			}
		}
		return cols.toString();
	}
}
