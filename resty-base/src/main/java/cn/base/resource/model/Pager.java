package cn.base.resource.model;

import java.util.List;

public class Pager {
	int pageNo; // 页码
	int pagePerCount; // 每页记录数
	int totalCount; // 总记录数

	int pm;// 分页列表方式 1.固定表格数据，列名等在开发时定义好在各自程序中，数据源使用各协议定义好的对像
			// 2.动态表格数据，数据由接口动态处理后，由页面显示，数据源使用 IPagerExt
	boolean hasHead = false; // 是否需要传递head 到终端

	long last_sync_time; // 同步时间戳

	int sort; // 1.顺序排列 2.倒序排列

	String sortCol = "id"; // 排序字段
	
	// 动态表格数据源
	List<String> heads; // 列表头信息， 由头信息可以决定页面表格显示的列 PM_DYNAMIC 支持类型
	List<List<String>> row; // heads 对应行数据源， PM_DYNAMIC 支持类型
	String sort_head; // 自定义排序列，必须为 heads 包含的列 PM_DYNAMIC 支持类型
	String id_head; // ID 列，必须为 heads 包含的列， 一般为heads首列 PM_DYNAMIC 支持类型

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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
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
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
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
