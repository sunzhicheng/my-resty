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
/**
 * 搜索辅助类
 * @author sunzhicheng
 *
 * @param <M>
 */
public class Query<M extends Model<M>> {
	private Map<String,String> q_item_list = new HashMap();

	public Map<String, String> getQ_item_list() {
		return q_item_list;
	}

	public void setQ_item_list(Map<String, String> q_item_list) {
		this.q_item_list = q_item_list;
	}
	
	public String getItem(String  key) {
		return this.q_item_list.get(key);
	}
	public String put(String  key,String value) {
		return this.q_item_list.put(key, value);
	}
}
