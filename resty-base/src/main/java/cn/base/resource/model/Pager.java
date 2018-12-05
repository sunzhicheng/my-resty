package cn.base.resource.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.base.resource.model.RestyBaseModel.HEAD_TYPE;
import cn.base.tool.ToolDTime;
import cn.base.tool.ToolException;
import cn.base.tool.ToolString;
import cn.dreampie.orm.Model;

public class Pager<M extends Model<M>> {
	int pageNo = 1; // 页码
	int pagePerCount = 10; // 每页记录数
	Long totalCount = 0L; // 总记录数

	int pm = 1;// 分页列表方式 1.固定表格数据，列名等在开发时定义好在各自程序中，数据源使用各协议定义好的对像
			// 2.动态表格数据，数据由接口动态处理后，由页面显示，数据源使用 IPagerExt
	boolean hasHead = false; // 是否已经有head   true:已经有了不需要传递到终端  false: 需要

	long last_sync_time; // 同步时间戳

	int sort = 1; // 1.顺序排列 2.倒序排列

	// 动态表格数据源
	List<String> heads; // 列表头信息， 由头信息可以决定页面表格显示的列 PM_DYNAMIC 支持类型
	List<List<String>> row; // heads 对应行数据源， PM_DYNAMIC 支持类型
	String sort_head; // 自定义排序列，必须为 heads 包含的列 PM_DYNAMIC 支持类型
	String id_head; // ID 列，必须为 heads 包含的列， 一般为heads首列 PM_DYNAMIC 支持类型

	// 固定表单需要保存的列表
	List<M> rowList = null;

	public List<M> getRowList() {
		return rowList;
	}

	public void setRowList(List<M> rowList) {
		this.rowList = rowList;
	}

	/**
	 * 默认动态表格显示字段
	 */
	private String[] head = { "ID", "创建时间", "更新时间" };
	private String[] colum = { "id", "create_at", "update_at" };
	private HEAD_TYPE[] type = { HEAD_TYPE.Long, HEAD_TYPE.Date, HEAD_TYPE.Date };
	
	public void setPageHead(String[] head, String[] colum, HEAD_TYPE[] type) {
		if (head.length != colum.length || colum.length != type.length) {
			throw ToolException.getEx("Dynamic table error, wrong length on head colum type");
		}
		this.head = head;
		this.colum = colum;
		this.type = type;
		if(ToolString.isNotNull(sort_head)) {
			sort_head = this.colum[ToolString.parseInt(sort_head)];
		}
	}
	
	/**
	 * 动态表格列标题，编译时初始化,避免反复创建对像 后续也可由数据库配置
	 */
	protected List<String> LIST_HEADS = new ArrayList<String>();
	/**
	 * 动态表格,每个表头对应的数据库字段
	 */
	protected Map<Integer, String> MAP_HEAD_COL = new HashMap<Integer, String>();

	/**
	 * 定义每一列的类型，处理数据的时候 根据类型处理
	 */
	protected Map<Integer, HEAD_TYPE> MAP_COL_TYPE = new HashMap<Integer, HEAD_TYPE>();

	/**
	 * 初始化动态表格的 表头配置
	 * 
	 * @return
	 */
	public void initPageHead() {
		// 初始化表头
		if(!hasHead && heads == null) {
			heads = new ArrayList<>();
		}
		LIST_HEADS.clear();
		LIST_HEADS.add("UUID");
		if(!hasHead)heads.add("UUID");
		
		for (int i = 0; i < head.length; i++) {
			LIST_HEADS.add(head[i]);
			if(!hasHead)heads.add(head[i]);
		}
		MAP_HEAD_COL.clear();
		MAP_HEAD_COL.put(0, "uuid");
		String[] colum_new = new String[colum.length+1];
		colum_new[0] = "uuid";
		for (int i = 0; i < colum.length; i++) {
			colum_new[i+1] = colum[i];
		}
		colum = colum_new;
		// 初始化表头和数据库关联
		for (int i = 0; i < colum.length; i++) {
			MAP_HEAD_COL.put(i, colum[i]);
		}

		// 每列类型
		MAP_COL_TYPE.clear();
		MAP_COL_TYPE.put(0, HEAD_TYPE.String);
		if (type != null) {
			for (int i = 0; i < type.length; i++) {
				MAP_COL_TYPE.put(i + 1, type[i]);
			}
		}
	}

	/**
	 * 绑定 动态表结构 的数据值
	 * 
	 * @param pager
	 * @param list
	 */
	public void bindIPagerRowData(List<M> list) {
		if (row == null) {
			row = new ArrayList<>();
		}
		// 如果传过来有数据 先清空
		if (row.size() > 0) {
			row.clear();
		}
		for (M m : list) {
			List<String> _row = new ArrayList();
			for (Integer key : MAP_HEAD_COL.keySet()) {
				Object v = m.get(MAP_HEAD_COL.get(key).trim());
				if (ToolString.isNull(v)) {
					_row.add("");
					continue;
				}
				if (MAP_COL_TYPE.isEmpty()) {
					_row.add(v + "");
				} else {
					HEAD_TYPE type = MAP_COL_TYPE.get(key);
					if (type == null) {
						_row.add(v + "");
						continue;
					}
					switch (type) {
					case Long:
					case String:
					case Double:
						_row.add(v + "");
						break;
					case Short_String:
						String shortStr = "";
						if (v != null) {
							shortStr = v.toString().substring(0, 10);
						}
						_row.add(shortStr);
						break;
					case Sex:
						// 1 男 2.女
						int gender = ToolString.parseInt(v.toString());
						String s = gender == 1 ? "男" : "女";
						_row.add(s);
						break;
					case Date:
						// 该系统所有时间都为Long 类型
						String date = ToolDTime.getDateStr((Long) v);
						_row.add(date);
						break;
					case State:
						Integer st = ToolString.parseInt(v.toString());
						_row.add(st + "");
						break;
					case Date_YMD:
						// 该系统所有时间都为Long 类型
						String date1 = ToolDTime.getDateStr((Long) v, ToolDTime.DEFAULT_DATE_FORMAT);
						_row.add(date1);
						break;
					default:
						_row.add(v + "");
						break;
					}
				}
			}
			row.add(_row);
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPagePerCount() {
		return pagePerCount;
	}

	public void setPagePerCount(int pagePerCount) {
		this.pagePerCount = pagePerCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPm() {
		return pm;
	}

	public void setPm(int pm) {
		this.pm = pm;
	}

	public boolean isHasHead() {
		return hasHead;
	}

	public void setHasHead(boolean hasHead) {
		this.hasHead = hasHead;
	}

	public long getLast_sync_time() {
		return last_sync_time;
	}

	public void setLast_sync_time(long last_sync_time) {
		this.last_sync_time = last_sync_time;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<String> getHeads() {
		return heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

	public List<List<String>> getRow() {
		return row;
	}

	public void setRow(List<List<String>> row) {
		this.row = row;
	}

	public String getSort_head() {
		return sort_head;
	}

	public void setSort_head(String sort_head) {
		this.sort_head = sort_head;
	}

	public String getId_head() {
		return id_head;
	}

	public void setId_head(String id_head) {
		this.id_head = id_head;
	}

}
