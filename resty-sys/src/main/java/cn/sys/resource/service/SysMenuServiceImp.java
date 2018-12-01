package cn.sys.resource.service;

import java.util.List;

import cn.dreampie.log.Logger;
import cn.sys.resource.model.SysMenu;

/**
 * 系统模块 菜单配置 服务类接口定义
 * 代码生成，文件存在则不重新生成
 * @author IDORP CODE GEN
 */
public class SysMenuServiceImp implements ISysMenuService<SysMenu> {
	private static Logger LOG = Logger.getLogger(SysMenuServiceImp.class);
//	/**
//	 * 通过UUID查询 菜单配置
//	 * @return IdSysMenuEntry.Builder
//	 */
//	public IdSysMenuEntry.Builder get(IdSysMenuEntry.Builder idSysMenuEntry) {
//		IdSysMenu.dao.get(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
//	/**
//	 * 普通查询 菜单配置
//	 * @return T
//	 */
//	public IdSysMenuEntry.Builder query(IdSysMenuEntry.Builder idSysMenuEntry) {
//		
//		IdSysMenu.dao.query(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
//	/**
//	 * 添加 菜单配置
//	 * @return IdSysMenuEntry.Builder
//	 */
//	public IdSysMenuEntry.Builder add(IdSysMenuEntry.Builder idSysMenuEntry) {
//		String permission = idSysMenuEntry.getProtoBuilder().getPermission();
////		if(ToolString.isNotNull(permission)){
////			if(IdSysMenu.dao.hasCol(IdSysMenu.COL_PERMISSION, permission, null)){
////				LOG.debug("该权限已经存在");
////				setEx(idSysMenuEntry.getTokenBuilder(), "permission_exist_error");
////				return idSysMenuEntry;
////			}
////		}
//		IdSysMenu.dao.add(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
//	/**
//	 * 更新 菜单配置
//	 * @return IdSysMenuEntry.Builder
//	 */
//	public IdSysMenuEntry.Builder update(IdSysMenuEntry.Builder idSysMenuEntry) {
//		String permission = idSysMenuEntry.getProtoBuilder().getPermission();
//		String uuid = ToolGP.getProtoQueryUUID(idSysMenuEntry);
////		if(ToolString.isNotNull(permission)){
////			if(IdSysMenu.dao.hasCol(IdSysMenu.COL_PERMISSION, permission, uuid)){
////				LOG.debug("该权限已经存在");
////				setEx(idSysMenuEntry.getTokenBuilder(), "permission_exist_error");
////				return idSysMenuEntry;
////			}
////		}
//		IdSysMenu.dao.update(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
//	/**
//	 * 删除 菜单配置
//	 * @return T
//	 */
//	public IdSysMenuEntry.Builder del(IdSysMenuEntry.Builder idSysMenuEntry) {
////		IdSysMenu.dao.delTree(idSysMenuEntry);
//		String uuid = ToolGP.getProtoQueryUUID(idSysMenuEntry);
//		IdSysMenu.dao.delTree(uuid, IdSysMenu.COL_PARENT_ID);
//		return idSysMenuEntry;
//	}
//
//	@Override
//	public IdSysMenuEntry.Builder tree(IdSysMenuEntry.Builder idSysMenuEntry) {
//		IdSysMenu.dao.tree(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
	@Override
	public List<SysMenu> hasMenu(SysMenu entry) {
		return SysMenu.dao.hasMenu(entry);
	}
//	@Override
//	public IdSysMenuEntry.Builder selectMenu(IdSysMenuEntry.Builder idSysMenuEntry) {
//		IdSysMenu.dao.selectMenu(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	@Override
//	public IdSysMenuEntry.Builder bindMenu(IdSysMenuEntry.Builder idSysMenuEntry) {
////		if(idSysMenuEntry.getProtoListCount()  ==  0) {
////			LOG.debug("需要绑定的菜单列表为空");
////			setEx(idSysMenuEntry.getTokenBuilder(), "menu_utype_empty_error");
////			return idSysMenuEntry;
////		}
//		IdSysMenu.dao.bindMenu(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
//	
//	public IdSysMenuEntry.Builder queryPostByFid(IdSysMenuEntry.Builder idSysMenuEntry) {
//		IdSysMenu.dao.queryPostByFid(idSysMenuEntry);
//		return idSysMenuEntry;
//	}
	
}