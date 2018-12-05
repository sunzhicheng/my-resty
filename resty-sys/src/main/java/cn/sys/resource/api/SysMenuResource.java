package cn.sys.resource.api;

import java.util.List;

import cn.base.resource.model.Pager;
import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.POST;
import cn.sys.resource.SysApiResource;
import cn.sys.resource.model.PlatformRestyModel;
import cn.sys.resource.model.SysMenu;
import cn.sys.resource.service.ISysMenuService;
import cn.sys.resource.service.SysServiceFactory;

/**
 * 系统模块 菜单配置 接口类
 * 代码生成，文件存在则不重新生成
 * @author IDORP CODE GEN
 */
@API(value="/menu")
public class SysMenuResource extends SysApiResource {
	private static Logger LOG = Logger.getLogger(SysMenuResource.class);
	private static ISysMenuService sysMenuService = SysServiceFactory
			.getInstance().getSysMenuService();
	
//	/**
//	 * 通过uuid查询 菜单配置
//	 * @param uuid
//	 * @return IdSysMenuEntry
//	 */
//	@GET("/:uuid")
//	public IdSysMenuEntry get(String uuid) {
//		IdSysMenuEntry.Builder idSysMenuEntry = IdSysMenuEntry.newBuilder();
//		idSysMenuEntry.getQueryBuilder().setUuid(uuid);
//		idSysMenuService.get(idSysMenuEntry);
//		return idSysMenuEntry.build();
//	}
//	
//	/**
//	 * 简单参数方式，参数放在URL中，有长度限制，但可以API级别缓存
//	 * 查询列表 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@GET("/query/:idSysMenuEntry")
//	public IdSysMenuEntry query(IdSysMenuEntry idSysMenuEntry) {
//		return idSysMenuService.query(idSysMenuEntry.toBuilder()).build();
//	}
//	
//	/**
//	 * 复杂参数方式，参数从 body 获取，参数无长度限制，不做API级别缓存
//	 * 查询列表 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@POST("/query")
//	public IdSysMenuEntry queryPost() {
//		return idSysMenuService.query(getProto().toBuilder()).build();
//	}
//	
//	@POST("/query/byfid")
//	public IdSysMenuEntry queryPostByFid() {
//		return idSysMenuService.queryPostByFid(getProto().toBuilder()).build();
//	}
//	
//	@POST("/tree")
//	public IdSysMenuEntry treePost() {
//		return idSysMenuService.tree(getProto().toBuilder()).build();
//	}
//	
	@POST("/hasMenu")
	public List<SysMenu> hasMenu(SysMenu entry) {
		Pager p = entry.getPager();
		LOG.debug("hasMenu::::::"+p);
		List<SysMenu> list = sysMenuService.hasMenu(entry);
		return list;
	}
//	
//	@POST("/selectMenu")
//	public IdSysMenuEntry selectMenu() {
//		return idSysMenuService.selectMenu(getProto().toBuilder()).build();
//	}
//	@POST("/bindMenu")
//	public IdSysMenuEntry bindMenu() {
//		return idSysMenuService.bindMenu(getProto().toBuilder()).build();
//	}
//	
//	/**
//	 * 增加 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@POST("/add")
//	public IdSysMenuEntry add() {
//		return idSysMenuService.add(getProto().toBuilder()).build();
//	}
//	
//	/**
//	 * 通过UUID更新 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@POST("/update")
//	public IdSysMenuEntry update() {
//		return idSysMenuService.update(getProto().toBuilder()).build();
//	}
//	
//	/**
//	 * 通过UUID删除 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@POST("/del")
//	public IdSysMenuEntry del() {
//		return idSysMenuService.del(getProto().toBuilder()).build();
//	}
//	
//	/**
//	 * 通过 UUID URL 参数删除 菜单配置
//	 * @return IdSysMenuEntry
//	 */
//	@POST("/del/:uuid")
//	public IdSysMenuEntry del(String uuid) {
//		IdSysMenuEntry.Builder idSysMenuEntry = IdSysMenuEntry.newBuilder();
//		idSysMenuEntry.getQueryBuilder().setUuid(uuid);
//		idSysMenuService.del(idSysMenuEntry);
//		return idSysMenuEntry.build();
//	}
	
}