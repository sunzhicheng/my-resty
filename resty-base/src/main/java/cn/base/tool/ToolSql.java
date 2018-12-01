package cn.base.tool;

import java.util.Collection;
import java.util.List;

import cn.base.resource.model.RestyBaseModel;

public class ToolSql {

	/**
	 * 遍历List   获取字段  colunm 的逗号字符串
	 * @param list
	 * @param colunm
	 * @return
	 */
	public static String list2Str(List<RestyBaseModel> list,String colunm){
		StringBuilder retn = new StringBuilder();
		for (RestyBaseModel m : list) {
			String v = m.get(colunm)+"";
			if(ToolString.isNull(v)){
				continue;
			}
			if(!ToolString.isNull(retn)){
				retn.append(RestyBaseModel.DOU);
			}
			retn.append(v);
		}
		return retn.toString();
	}

	/**
	 * 把11,22,33...转成'11','22','33'...
	 * 
	 * @param ids
	 * @return
	 */
	public static String toSql(String ids) {
		return toSql(ids, RestyBaseModel.DOU);
	}

	/**
	 * 把11,22,33...转成'11','22','33'...
	 * 
	 * @param ids
	 * @return
	 */
	public static String toSql(String ids, String split) {
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
	public static String toSql(String[] idsArr) {
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

	public static String toSql(long[] idsArr) {
		if (idsArr == null || idsArr.length == 0) {
			return null;
		}

		StringBuilder sqlSb = new StringBuilder();

		for (long id : idsArr) {
			if (sqlSb.length() > 0) {
				sqlSb.append(RestyBaseModel.DOU);
			}
			sqlSb.append(id);
		}
		return sqlSb.toString();
	}
	
	/**
	 * 从List 集合对象中 获取col  字段值的带逗号的字符串
	 * @param list
	 * @param col
	 * @return
	 */
	public  String appendCol(Collection<RestyBaseModel> list, String col) {
		StringBuilder cols = new StringBuilder();
		for (RestyBaseModel m : list) {
			if(m.get(col) != null){
				if (!ToolString.isNull(cols)) {
					cols.append(RestyBaseModel.DOU);
				}
				cols.append(m.get(col) + "");
			}
		}
		return cols.toString();
	}
	
	/**
	 * 生成时间间隔查询SQL
	 * 
	 * @param colName
	 * @return String 时间间隔查询
	 */
	public String betweenTimeSql(String colName) {
		StringBuilder where = new StringBuilder();
		where.append(" ");
		where.append(colName);
		where.append(">=");
		where.append(RestyBaseModel.SQL_WEN);
		where.append(RestyBaseModel.SQL_AND);
		where.append(colName);
		where.append("<=");
		where.append(RestyBaseModel.SQL_WEN);
		where.append(" ");
		return where.toString();
	}
	
	
}
