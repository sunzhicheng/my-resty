package cn.sys.resource.model;

import cn.base.annotation.OutJson;
import cn.base.resource.model.Pager;
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
	@OutJson
	private Pager pager;
	
	/**
	 * SQL 排序字符串
	 */
	public static final String SQL_ORDER_BY = " ORDER BY ";
	public static final String SQL_ORDER_ASC = " ASC ";
	public static final String SQL_ORDER_DESC = " DESC ";
	public static final int PAGE_SPILT_MAX_SIZE = 10;

}
