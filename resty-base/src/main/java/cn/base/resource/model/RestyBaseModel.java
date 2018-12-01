package cn.base.resource.model;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.base.annotation.OutJson;
import cn.base.tool.ToolSql;
import cn.base.tool.ToolString;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.Model;
import cn.dreampie.orm.TableMeta;
import cn.dreampie.orm.dialect.Dialect;
import cn.dreampie.route.annotation.API;

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

	private void setJsonOutField(Class<?> clz, RestyBaseModel model) {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {// --for() begin
			OutJson outJson = field.getAnnotation(OutJson.class);
			
			if (outJson != null) {
				// 获取原来的访问控制权限
				boolean accessFlag = field.isAccessible();
				// 修改访问控制权限
				field.setAccessible(true);
				if (outJson.render()) {
					String fieldType = field.getGenericType().toString();
					try {
						Object value = field.get(model);
						if (value instanceof ArrayList) {
							List list = (ArrayList) value;
							for (int i = 0; i < list.size(); i++) {
								Object obj = list.get(i);
								if (obj instanceof RestyBaseModel) {
									LOG.debug("#############"+obj.getClass().getTypeName());
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
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
