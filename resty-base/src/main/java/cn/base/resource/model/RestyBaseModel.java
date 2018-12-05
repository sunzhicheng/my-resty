package cn.base.resource.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import cn.base.tool.ToolDTime;
import cn.base.tool.ToolSql;
import cn.base.tool.ToolString;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.Model;
import cn.dreampie.orm.TableMeta;
import cn.dreampie.orm.dialect.Dialect;
import cn.dreampie.orm.page.Page;

/**
 * Model基础类
 * 
 * @author sunzc
 * @param <M>
 */
public abstract class RestyBaseModel<M extends Model<M>> extends Model<M> {
	private static Logger LOG = Logger.getLogger(RestyBaseModel.class);
	private static final long serialVersionUID = -900378319414539856L;

	public static final String TIPS_NO_PAGER = "缺少分页信息";

	public String[] EXT_CLOS = new String[0];

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
	public static final String ID = "id";

	public static final String DOU = ",";
	public static final String DENG_YU = "=";
	public static final String SQL_AND = " AND ";
	public static final String SQL_OR = " OR ";
	public static final String SQL_WEN = "? ";
	public static final String SQL_DENG_YU_WEN = "=? ";
	public static final String JIN = "#";
	public static final String STRING_FORMAT = "%s";

	/**
	 * SQL 排序字符串
	 */
	public static final String SQL_ORDER_BY = " ORDER BY ";
	public static final String SQL_ORDER_ASC = " ASC ";
	public static final String SQL_ORDER_DESC = " DESC ";
	
	/**
	 * sql 总数
	 */
	public static final String COUNT_SQL = "select count(*) as counts from  ( %s ) t   ";

	/**
	 * LONG 主键 确认数据库存在主键
	 * 
	 * @return
	 */
	public long getID() {
		return getLong(ID);
	}

	public String getUUID() {
		return getStr(UUID);
	}

	public String getPK() {
		return this.UUID;
	}

	/**
	 * 获取主键值：非复合主键
	 * 
	 * @return
	 */
	public String getPKValue() {
		return this.getUUID();
	}

	/**
	 * 防止脏数据 update方法
	 */
	public boolean updateForVersion() {
		TableMeta table = getTableMeta();
		boolean hasVersion = table.hasColumn("version");

		if (hasVersion) {// 是否需要乐观锁控制，表是否有version字段
			String name = table.getTableName();
			String pk = table.getGeneratedKey();

			// 1.数据是否还存在
			Model<M> modelOld = findFirst("select version from " + name + " where " + pk + " = ?", get(pk));
			if (null == modelOld) { // 数据已经被删除
				throw new RuntimeException("数据库中此数据不存在，可能数据已经被删除，请刷新数据后在操作");
			}

			// 2.乐观锁控制
			Long versionDB = modelOld.get("version"); // 数据库中的版本号
			Long versionForm = Long.parseLong(get("version") + ""); // 表单中的版本号
			if (versionForm != versionDB) {
				throw new RuntimeException("表单数据版本号和数据库数据版本号不一致，可能数据已经被其他人修改，请重新编辑");
			}
			set("version", versionDB + 1);
		}
		return super.update();
	}

	public long findCount(String sql, Object... params) {
		Model<M> m = findFirst(sql, params);
		if (m != null) {
			long counts = m.get("counts");
			return counts;
		} else {
			return 0;
		}
	}

	public M findByUuid(String uuid) {
		TableMeta tableMeta = getTableMeta();
		Dialect dialect = getDialect();
		String sql = dialect.select(tableMeta.getTableName(), "", UUID + "=?");
		return findFirst(sql, uuid);
	}

	/**
	 * 安全取值
	 * 
	 * @param name
	 * @return
	 */
	public String getStr(String name) {
		Object value = this.get(name);
		if (null != value) {
			// 解决强转出错
			return String.valueOf(value);
		} else
			return "";
	}

	/**
	 * 安全取值 只转换正数
	 * 
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		String value = this.getStr(name);
		if (ToolString.isNumeric(value)) {
			return Long.parseLong(value);
		} else {
			return 0;
		}
	}

	/**
	 * 安全取值 只转换正数 只取前10位
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
	 * 安全取值 只转换正数
	 * 
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

	/**
	 * 覆盖框架 delete 方法(更新 status=2 ) ：该对象应该要有 status 字段 和主键字段名为id
	 */
	public boolean delete() {
		StringBuilder where = new StringBuilder(getPK());
		where.append(DENG_YU);
		where.append(getPKValue());
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}

	/**
	 * 覆盖框架 deleteById 方法(更新 status=2 ) ：该对象应该要有 status 字段 和主键字段名为id
	 */
	public boolean deleteById(Object id) {
		StringBuilder where = new StringBuilder(ID);
		where.append(DENG_YU);
		where.append(id);
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}

	public boolean deleteByUUID(String uuid) {
		StringBuilder where = new StringBuilder(UUID);
		where.append(DENG_YU);
		where.append("'" + uuid + "'");
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}

	/**
	 * 通过uuid1,uuid2 字符串批量删除
	 */
	public boolean deleteInUuids(String uuids) {
		StringBuilder where = new StringBuilder(UUID);
		where.append(" in ( " + ToolSql.toSql(uuids) + ")");
		return updateColsBy(SQL_STATUS, where.toString(), 2);
	}

	/**
	 * 根据需要IN查询的字段 进行where 查询
	 * 
	 * @param where    a=?
	 * @param inCol    col_in
	 * @param InValues 1,2,3
	 * @param params   a 的值
	 * @return
	 */
	public List<M> findInByCol(String where, String inCol, String InValues, Object... params) {
		if (!ToolString.isNull(where)) {
			where += " and ";
		}
		List<M> list = findBy(where + "  " + inCol + " in (" + ToolSql.toSql(InValues.toString()) + ") ", params);
		return list;
	}

	/**
	 * 1,2,3 根据id 获取 id,model 的MAP
	 * 
	 * @param ids
	 * @return
	 */
	public Map<Long, M> mapById(String ids) {
		List<M> list = findInIds((Object[]) ids.split(DOU));
		return list2MapById(list);
	}

	/**
	 * 对象的List 转换成 key为iid 的 Map 对象
	 * 
	 * @param list
	 * @return
	 */
	public Map<Long, M> list2MapById(List<M> list) {
		Map<Long, M> retnMap = new HashMap<Long, M>();
		for (M m : list) {
			retnMap.put(Long.valueOf(m.get(ID).toString()), m);
		}
		return retnMap;
	}

	/**
	 * 对象的List 转换成 key为uuid 的 Map 对象
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, M> list2MapByUUID(List<M> list) {
		Map<String, M> retnMap = new HashMap<String, M>();
		for (M m : list) {
			retnMap.put(m.get(UUID).toString(), m);
		}
		return retnMap;
	}

	/**
	 * 对象的List 转换成 key为c传入的 Map 对象
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, M> list2MapByCol(List<M> list, String col) {
		Map<String, M> retnMap = new HashMap<String, M>();
		for (M m : list) {
			retnMap.put(m.get(col).toString(), m);
		}
		return retnMap;
	}
	
	/**
	 * 默认动态表格显示字段
	 */
	private String[] head = { "UUID","ID", "创建时间", "更新时间" };
	private String[] colum = { "uuid","id", "create_at", "update_at" };
	private HEAD_TYPE[] type = { HEAD_TYPE.Long, HEAD_TYPE.Date, HEAD_TYPE.Date };

	public enum HEAD_TYPE {
		NULL, State, Long, Sex, String,Short_String,Double, Integer, Date, Date_YMD,Date_HS
	}
	
	
	
	

	
	/**
	 * 根据IPager 和 AIC 数据库分页和数据过滤的配置查询 数据
	 * 
	 * @param page
	 * @param select
	 * @param from_sql
	 * @param acc
	 * @param params
	 * @return
	 */
	protected Pager<M> splitPage(Pager<M> pager, String pSql,Object... params) {
		int pageNo = pager.getPageNo();
		int pageSize = pager.getPagePerCount();
		LOG.debug("request pageNo:" + pageNo + "       pageSize:" + pageSize);
		if (pageNo == 0) {
			pageNo = 1;
		}
		// 更新同步时间
		pager.setLast_sync_time(ToolDTime.getNowL());
		// eg.select count(*) from table
		if(pager.getTotalCount() == 0) {
			String count_sql = pSql;
			if(count_sql.contains(SQL_ORDER_BY)) {
				count_sql = count_sql.substring(0, count_sql.indexOf(SQL_ORDER_BY));
			}
			StringBuilder countSql = new StringBuilder(String.format(COUNT_SQL, count_sql));
			// eg.from table
			StringBuilder sql = new StringBuilder(pSql);

			// 行级：过滤
			// LOG.debug("是否设置行级过滤 :" + aic.isRowFilter());
			// if (aic.isRowFilter()) {
			// rowFilter(sql);
			// }
			// set总记录数
			long rowCount = findCount(countSql.toString(), params);
			pager.setTotalCount(rowCount);
		}
		StringBuilder sql = new StringBuilder(pSql);

		if (!pSql.contains(SQL_ORDER_BY)) { // 包含order by 的情况 不处理
			String sortCol = pager.getSort_head();
			if(ToolString.isNull(sortCol)) {
				sortCol = ID;
			}
			int sortBy = pager.getSort();
			LOG.debug("splitPage ,sortCol: " + sortCol + "      and    sortBy:" + sortBy);
			sql.append(SQL_ORDER_BY);
			sql.append(sortCol);
			sql.append("   ");
			sql.append(sortBy == 2?SQL_ORDER_DESC:SQL_ORDER_ASC );
		}
		switch (pager.getPm()) {
		case 1:
			LOG.debug("请求<固定>表格查询");
			// 固定表格方式只返回对象List;
			Page<M> pageModer = paginate(pageNo, pageSize, sql.toString(), params);
			pager.setRowList(pageModer.getList());
			return pager;
		case 2:
			// 初始化表头配置
			pager.initPageHead();
			if (pager.MAP_HEAD_COL.isEmpty()) {
				LOG.error("**********未初始化数据库字段集合 MAP_HEAD_COL**********");
				return pager;
			}
			if (pager.LIST_HEADS.isEmpty()) {
				LOG.error("**********未初始化表头集合 LIST_HEADS**********");
				return pager;
			}

			List<M> bindList = new ArrayList<M>();
			// 分页处理 是否分页
			Page<M> pm = paginate(pageNo, pageSize, sql.toString(), params);
			bindList = pm.getList();

			LOG.debug("动态表格模式生成结果集Size():" + bindList.size());
			// 设在IDhead 一般以第一个
			pager.setId_head("UUID");
			// set IPagerRowData 集合
			pager.bindIPagerRowData(bindList);
			// 动态表格模式不需要返回真实结果 ，数据已经封装在page 对象里面 ，所以返回空结果集
			return pager;
		default:
			LOG.debug("不支持的分页列表方式:" + pager.getPm());
			// 不返回数据
			return pager;
		}
	}

	private void setJsonOutField(Class<?> clz, RestyBaseModel model) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {// --for() begin
			JSONField jsonField = field.getAnnotation(JSONField.class);
			
			if (jsonField != null) {
				// 获取原来的访问控制权限
				boolean accessFlag = field.isAccessible();
				// 修改访问控制权限
				field.setAccessible(true);
				if (jsonField.serialize()) {
					String fieldType = field.getGenericType().toString();
					try {
						Object value = field.get(model);
						if (value instanceof ArrayList) {
							List list = (ArrayList) value;
							for (int i = 0; i < list.size(); i++) {
								Object obj = list.get(i);
								if (obj instanceof RestyBaseModel) {
									setJsonOutField(obj.getClass(),(RestyBaseModel)obj);
								}
							}
						} else if (value instanceof Map) {
							HashMap map = (HashMap) value;
							for (Object key : map.keySet()) {
								Object obj = map.get(key);
								if (obj instanceof RestyBaseModel) {
									setJsonOutField(obj.getClass(),(RestyBaseModel)obj);
								}
							}
						}
						
						if (value != null) {
							model.put(field.getName(), value);
						}
						field.setAccessible(accessFlag);
					} catch (Exception e) {
						LOG.error("setJsonOutField  error ",e);
					}
				}
			}
		}
	}

	private Class<?> getPlatformRestyModel(Class<?> clz) {
		if (clz.toString().equals("class cn.sys.resource.model.PlatformRestyModel")) {
			return clz;
		} else {
			return getPlatformRestyModel(clz.getSuperclass());
		}
	}
	
	/**
	 * 输出标识OutJson 的标签 的字段到  客户端JSON
	 * @return
	 */
	public RestyBaseModel<M> buildRender() {
		Class<?> clz = this.getClass();
		// 设置用户自定义的JSON输出字段
		this.setJsonOutField(clz,this);
		// 设置系统平台自定义JSON输出字段
		this.setJsonOutField(getPlatformRestyModel(clz),this);
		return this;
	}

	/**
	 * 行级：过滤
	 * 
	 * @return
	 */
	protected void rowFilter(StringBuilder formSqlSb) {

	}
}
