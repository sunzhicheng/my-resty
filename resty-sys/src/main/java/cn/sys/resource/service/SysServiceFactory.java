package cn.sys.resource.service;

import cn.dreampie.orm.aspect.AspectFactory;
import cn.dreampie.orm.transaction.TransactionAspect;
import cn.sys.resource.model.SysMenu;
import cn.sys.resource.model.SysUser;

/**
 * 系统管理服务
 * @author haoyin
 *
 */
public class SysServiceFactory {
//	private static Logger LOG = Logger.getLogger(IdBaseServiceFactory.class);
	private volatile static SysServiceFactory instance;
	/**
	 * 菜单服务
	 */
	private ISysMenuService<SysMenu> sysMenuService;
	/**
	 * 用户服务
	 */
	private ISysUserService<SysUser> sysUserService;
	
	
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
	 * 菜单服务
	 * @return 菜单服务
	 */
	public ISysMenuService<SysMenu> getSysMenuService() {
		if (sysMenuService == null) {
			sysMenuService = AspectFactory.newInstance(new SysMenuServiceImp(), new TransactionAspect());
		}
		return sysMenuService;
	}
	
	/**
	 * 用户服务
	 * @return 用户服务
	 */
	public ISysUserService<SysUser> getSysUserService() {
		if (sysUserService == null) {
			sysUserService = AspectFactory.newInstance(new SysUserServiceImp(), new TransactionAspect());
		}
		return sysUserService;
	}
	
	
}