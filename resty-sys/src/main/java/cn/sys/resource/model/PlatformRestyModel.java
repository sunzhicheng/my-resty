package cn.sys.resource.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import cn.base.resource.model.Pager;
import cn.base.resource.model.Query;
import cn.base.resource.model.RestyBaseModel;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.Model;

/**
 * Model基础类
 * 
 * @author sunzc
 * @param <M>
 */
public abstract class PlatformRestyModel<M extends Model<M>> extends RestyBaseModel<M> {
	private static Logger LOG = Logger.getLogger(PlatformRestyModel.class);
	private static final long serialVersionUID = -900378319414539856L;
	
	//分页对象
	@JSONField
	private Pager pager;
	
	private Query query;
	
	public Pager getPager() {
		if(this.pager == null) {
			Pager p = (Pager) JSON.parseObject(this.get("pager")+"",Pager.class);
			this.pager = p;
		}
		return pager;
	}
	public Query getQuery() {
		if(this.query == null) {
			Query q = (Query) JSON.parseObject(this.get("query")+"",Query.class);
			this.query = q;
		}
		return query;
	}
}
