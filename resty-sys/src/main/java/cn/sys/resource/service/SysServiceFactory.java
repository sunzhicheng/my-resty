package cn.sys.resource.service;

import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.sys.resource.model.SysMenu;

/**
 * 系统管理服务
 * @author haoyin
 *
 */
public class SysServiceFactory {
//	private static Logger LOG = Logger.getLogger(IdBaseServiceFactory.class);
	private volatile static SysServiceFactory instance;
	/**
	 * 认证服务
	 */
	private ISysMenuService<SysMenu> sysMenuService;
	
	
	private SysServiceFactory() {
	}

	public static SysServiceFactory getInstance() {
		if (instance == null) {

			// 只有第一次才彻底执行这里的代码
			synchronized (SysServiceFactory.class) {
				// 再检查一次
				if (instance == null) {
					instance = new SysServiceFactory();
				}
			}
		}

		return instance;
	}
	
	/**
	 * 返回任务调度服务
	 * @return 认证服务
	 */
	public ISysMenuService<SysMenu> getSysMenuService() {
		if (sysMenuService == null) {
			sysMenuService = AspectFactory.newInstance(new SysMenuServiceImp(), new TransactionAspect());
		}
		return sysMenuService;
	}
	
	
	
	
}