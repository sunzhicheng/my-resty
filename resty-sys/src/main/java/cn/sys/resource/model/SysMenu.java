package cn.sys.resource.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import cn.base.tool.ToolString;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.annotation.Table;
import cn.sys.resource.model.abs.ISysMenuAbs;

/**
 * 系统模块 菜单配置 实现类
 * 代码生成，文件存在则不重新生成
 * @author Idorp Code Gen
 */
@Table(name = "sys_menu", primaryKey = "uuid", cached = true)
public class SysMenu extends ISysMenuAbs<SysMenu> implements Serializable{
	private static final long serialVersionUID = -1L;
	private static Logger LOG = Logger.getLogger(SysMenu.class);
	public static SysMenu dao = new SysMenu();
	@JSONField
	private ArrayList<SysMenu> sub_list = new ArrayList<>();
	
	public static  final String   hasMenu = "select menu.*   from   id_sys_menu  menu  inner join id_sys_user_type_menu  userTypeMenu   on userTypeMenu.menu_id= menu.uuid where userTypeMenu.sql_status=1 and  menu.sql_status=1   ";
	
	
	
	public List<SysMenu> hasMenu(SysMenu entry) {
		List<SysMenu> retnList = new ArrayList<>();
		List<SysMenu>  menuList  =   findAll();
		String fUuid = "";
		if(menuList != null && menuList.size() > 0){
			for (SysMenu SysMenu : menuList) {
				if(ToolString.isNull(SysMenu.getParentId())) {
					fUuid = SysMenu.getUuid();
					break;
				}
			}
			List<SysMenu> rootList = getRootMenu(menuList,fUuid);
			for (SysMenu idSysMenu : rootList) {
				retnList.add(initRootMenu(idSysMenu, menuList));
			}
		}
		return retnList;
	}
	
//	public List<SysMenu>  getOwnMenu(IToken.Builder token) {
//		IToken.Builder fullToken = auth.getFullToken();
//		List<SysMenu> list = getOwnMenu( fullToken,true);
//		if(fullToken.hasEx()) {
//			token.setEx(fullToken.getEx());
//		}
//		return list;
//	}
//	
//	
//	/**
//	 * 获取该用户已经配置的菜单
//	 * @param token
//	 * @return
//	 */
//	public List<SysMenu>  getOwnMenu(IToken.Builder token,boolean isAdmin) {
//		String userUuid = token.getExtBuilder().getUserIdBuilder().getOpenId();
//		StringBuilder sql  = new StringBuilder();
//		List<SysMenu> retnList = new ArrayList<SysMenu>();
//		IAccessSource accsource = token.getAccSource();
//		if( IAccessSource.AAS_MANAGER == accsource  &&  token.getExtBuilder().getTExt2().equals("operate")) {  //运营
//			IdSysAppAcount  accout = IdSysAppAcount.dao.findByUuid(userUuid);
//			if(accout == null){
//				LOG.error("用户不存在");
//				token.setEx(IdSysEx.getEx(IdSysEx.getLangStr(token.getExtBuilder().getLang(), "inner_error")));
//				return retnList;
//			}
//			//超级管理员   查看所以后台菜单
//			if(accout.getUserType() == 1) {
//				sql.append(getDialect().select(getTableMeta().getTableName(),"   ","  sql_status=1 and menu_type=1 "));
//				if(isAdmin) {
//					sql.append(" and is_admin=1 ");
//				}
//				sql.append("  order by sort ");
//			} else {   //一般管理员
//				String userType = accout.getSysUserUuid();
//				if(ToolString.isNull(userType)) {
//					LOG.debug("没有绑定菜单权限");
//					token.setEx(IdSysEx.getEx(IdSysEx.getLangStr(token.getExtBuilder().getLang(), "menu_no_role_error")));
//					return retnList;
//				}
//				sql.append(hasMenu);
//				sql.append(" and menu.menu_type=1 ");
//				sql.append(" and userTypeMenu.user_type_id='"+userType+"'   ");
//				if(isAdmin) {
//					sql.append(" and menu.is_admin=1 ");
//				}
//				sql.append(" order by menu.sort ");
//			}
//		}else if(IAccessSource.AAS_WX == accsource || IAccessSource.AAS_MIN_PRO == accsource || IAccessSource.AAS_APP == accsource || token.getExtBuilder().getTExt2().equals("business") ){  //微信  app  商户 端 
//			
//			IdSysUser user = IdSysUser.dao.findByUuid(userUuid);
//			String userType = user.getIdUserTypeId();
//			if(ToolString.isNull(userType)) {
//				LOG.debug("没有绑定菜单权限");
//				token.setEx(IdSysEx.getEx(IdSysEx.getLangStr(token.getExtBuilder().getLang(), "menu_no_role_error")));
//				return retnList;
//			}
//			sql.append(hasMenu);
//			if(IAccessSource.AAS_WX == accsource) {
//				sql.append(" and menu.menu_type=3");
//			}else if(IAccessSource.AAS_MIN_PRO == accsource) {
//				sql.append(" and menu.menu_type=3");
//			}else if(IAccessSource.AAS_APP == accsource){
//				sql.append(" and menu.menu_type=2");
//			}else {
//				sql.append(" and menu.menu_type=4");
//			}
//			sql.append(" and userTypeMenu.user_type_id='"+userType+"'   ");
////			if(isAdmin) {
////				sql.append(" and menu.is_admin=1 ");
////			}
//			sql.append(" order by menu.sort ");
//		}
//		//普通情况  需要根据用户类型绑定的相应菜单加载
//		if(ToolString.isNull(sql)) {
//			LOG.debug("非法来源");
//			token.setEx(IdSysEx.getEx("非法来源"));
//			return retnList;
//		}
//		retnList = find(sql.toString());
//		return retnList;
//	}
//	/**
//	 * 获取该用户已经配置的操作权限
//	 * @param menuType   菜单类型   1：pc 2：app 3：微信端
//	 * @return
//	 */
//	public List<String> getPermissionList(int menuType) {
//		List<String> permissionList = new ArrayList<String>();
//		IToken.Builder token = auth.getFullToken();
//		List<SysMenu>  menuList = getOwnMenu(token,true);
//		for (SysMenu SysMenu : menuList) {
//			if(ToolString.isNotNull(SysMenu.getPermission())){
//				permissionList.add(SysMenu.getPermission());
//			}
//		}
//		return permissionList;
//	}
//	
//	public void tree(SysMenuEntry.Builder protoMsg) {
//		String menu_type = getQueryItem(protoMsg.getQueryBuilder().getQItemListList(),COL_MENU_TYPE ).toString();
//		StringBuilder where  = new StringBuilder("  sql_status=1  ");
//		if(!ToolString.isNull(menu_type)){
//			where.append("  and menu_type="+menu_type);
//		}
//		where.append("  order by sort ");
//		String sql  = getDialect().select(getTableMeta().getTableName(),"   ",where.toString());
//		List<SysMenu> menuList = find(sql);
//		List<SysMenu> rootList = getRootMenu(menuList,null);
//		for (SysMenu SysMenu : rootList) {
//			protoMsg.addProtoList(initRootMenu(SysMenu, menuList));
//		}
//	}
//	
//	public void bindMenu(SysMenuEntry.Builder protoMsg) {
//		String uTypeId = getQueryItem(protoMsg.getQueryBuilder().getQItemListList(),"uTypeId" ).toString();
//		if(ToolString.isNull(uTypeId)) {
//			LOG.debug("需要绑定的用户类型为空");
//			protoMsg.getTokenBuilder().setEx(IdSysEx.getEx(IdSysEx.getLangStr(protoMsg.getTokenBuilder().getExtBuilder().getLang(), "menu_select_empty_error")));
//			return ;
//		}
//		//先更新uType 类型的所有关联 状态为2
//		IdSysUserTypeMenu.dao.delByUtype(uTypeId);
//		List<idsys.TbSysMenu.SysMenu.Builder> munuBuildList = protoMsg.getProtoListBuilderList();
//		if(protoMsg.getProtoListCount()>0) {
//			StringBuilder menuUuidStr = new StringBuilder();
//			for (idsys.TbSysMenu.SysMenu.Builder menuBuilder : munuBuildList) {
//				if(!ToolString.isNull(menuUuidStr)) {
//					menuUuidStr.append(",");
//				}
//				menuUuidStr.append(menuBuilder.getDtcBuilder().getPtIdBuilder().getOpenId());
//			}
//			List<IdSysUserTypeMenu> menuTypeList =  IdSysUserTypeMenu.dao.findInByCol("  user_type_id=? ", "menu_id", menuUuidStr.toString(),uTypeId);
//			Map<String,IdSysUserTypeMenu>  menuType = IdSysUserTypeMenu.dao.list2MapByCol(menuTypeList, "menu_id");
//			
//			//对于传过来的menu   数据库里面有关联关系 直接修改状态为1   如果没有  就创建
//			for (idsys.TbSysMenu.SysMenu.Builder menuBuilder : munuBuildList) {
//				String uuid_in = menuBuilder.getDtcBuilder().getPtIdBuilder().getOpenId();
//				IdSysUserTypeMenu existMunuType = menuType.get(uuid_in);
//				//判断是否存在
//				boolean flag = false;
//				if(existMunuType != null) {
//					//如果存在   更新状态为1
//					flag = existMunuType.setSqlStatus(1).update();
//				}else {
//					//没有就创建
//					flag = IdSysUserTypeMenu.dao.saveMenuType(uTypeId,uuid_in);
//				}
//				if( !flag){
//					setEx(protoMsg.getTokenBuilder(), "db_update_error");
//					throw new RuntimeException("SysMenu.bindMenu()  保存或更新失败.  ");
//				}
//			}	
//			purgeCache();
//		}
//	}
//	
//	public void selectMenu(SysMenuEntry.Builder protoMsg) {
//		IToken.Builder fullToken = auth.getFullToken();
//		StringBuilder sql = new StringBuilder();
//		String useruuid = fullToken.getExtBuilder().getUserIdBuilder().getOpenId();
//		IAccessSource accsource = fullToken.getAccSource();
//		IdSysAppAcount acount = IdSysAppAcount.dao.findByUuid(useruuid);
//		if(IAccessSource.AAS_MANAGER == accsource  &&  fullToken.getExtBuilder().getTExt2().equals("operate") && acount.getUserType() ==1) { //运营平台用户  且 user_type  =1  给所有菜单
//			sql.append(getDialect().select(getTableMeta().getTableName(),"   ","  sql_status=1 "));
//		}else {
//			String userType ="";
//			if(fullToken.getExtBuilder().getTExt2().equals("operate")) {
//				userType = acount.getSysUserUuid();
//			}else {
//				IdSysUser user = IdSysUser.dao.findByUuid(useruuid);
//				userType = user.getIdUserTypeId();
//			}
//			if(ToolString.isNull(userType)) {
//				protoMsg.getTokenBuilder().setEx(ComEx.getEx("该用户没有配置用户类型"));
//				return;
//			}
//			sql.append(hasMenu);
//			sql.append(" and userTypeMenu.user_type_id='"+userType+"'   ");
//		}
//		List<SysMenu>  menuList  =  find(sql.toString());
//		List<SysMenu> rootList = getRootMenu(menuList,null);
//		for (SysMenu SysMenu : rootList) {
//			protoMsg.addProtoList(initRootMenu(SysMenu, menuList));
//		}
//		//通过用户类型 查询已经绑定过的属性
//		String uTypeId = getQueryItem(protoMsg.getQueryBuilder().getQItemListList(),"uTypeId" ).toString();
//		List<IdSysUserTypeMenu> hasTypeMenuList = IdSysUserTypeMenu.dao.byUtype(uTypeId);
//		//已经绑定的菜单
//		StringBuilder hasMenuIds = new StringBuilder(); 
//		for (IdSysUserTypeMenu idSysUserTypeMenu : hasTypeMenuList) {
//			if(ToolString.isNotNull(hasMenuIds)){
//				hasMenuIds.append(",");
//			}
//			hasMenuIds.append(idSysUserTypeMenu.getMenuId());
//		}
//		protoMsg.getProtoBuilder().setParentIds(hasMenuIds.toString());
//	}
	
	
	public SysMenu initRootMenu(SysMenu rootDB ,List<SysMenu> menuList) {
		List<SysMenu>  subMenuList = getSubMenuList(menuList, rootDB.getUuid());
		if(subMenuList == null || subMenuList.size() == 0){
			return rootDB;
		}else {
			for (SysMenu SysMenu : subMenuList) {
				rootDB.sub_list.add(initRootMenu(SysMenu, menuList));
			}
			return rootDB;
		}
	}
	
	/**
	 * 获取根菜单列表
	 * @param menuList
	 * @return
	 */
	public List<SysMenu> getRootMenu(List<SysMenu> menuList,String parentId){
		List<SysMenu> rootList = new ArrayList<SysMenu>();
		for (SysMenu SysMenu : menuList) {
			if(ToolString.isNull(parentId)) {
				if(ToolString.isNull(SysMenu.getParentId())) {
					rootList.add(SysMenu);
				}
			}else {
				if(parentId.equals(SysMenu.getParentId())) {
					rootList.add(SysMenu);
				}
			}
		}
		return rootList;
	}
	/**
	 * 根据父UUID 查找 对应的子菜单列表
	 * @param menuList
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> getSubMenuList(List<SysMenu> menuList , String parentId){
		List<SysMenu> subList = new ArrayList<SysMenu>();
		for (SysMenu SysMenu : menuList) {
			if(!ToolString.isNull(parentId) && !ToolString.isNull( SysMenu.getParentId()) 
					&& SysMenu.getParentId().equals(parentId)) {
				subList.add(SysMenu);
			}
		}
		return subList;
	}
//
//	public static final String byFUuid = "select * from id_sys_menu where sql_status=1 ";
//	public List<SysMenu> byFUuid(String fuuid) {
//		StringBuilder sql = new StringBuilder(byFUuid);
//		if(ToolString.isNotNull(fuuid)) {
//			sql.append("and parent_id=?");
//			return find(sql.toString(), fuuid);
//		}else {
//			sql.append("and parent_id is null ");
//			return find(sql.toString());
//		}
//	}
//	
//	public void queryPostByFid(Builder protoMsg) {
//		String pId = getQueryItem(protoMsg.getQueryBuilder().getQItemListList(), "pId").toString();
//		
//		List<SysMenu> menuList = byFUuid(pId);
//		for (SysMenu menu : menuList) {
//			idsys.TbSysMenu.SysMenu.Builder menuBuilder = idsys.TbSysMenu.SysMenu.newBuilder();
//			ToolGP.mergeProtoBase(menuBuilder, menu.getAttrs());
//			protoMsg.addProtoList(menuBuilder);
//		}
//	}
	
}