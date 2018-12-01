package cn.sys.resource.service;

import java.util.List;

import cn.dreampie.orm.transaction.Transaction;
import cn.sys.resource.model.SysMenu;

/**
 * 系统模块 菜单配置 服务类接口定义
 * 代码生成，文件存在则不重新生成
 * @author IDORP CODE GEN
 */
public interface ISysMenuService<T extends SysMenu> {
	
	public static final String USER_PERMISSION_GROUP="user_permission_group";
//	/**
//	 * 通过UUID查询 菜单配置
//	 * @return T
//	 */
//	public T get(T idSysMenuEntry);
//	
//	/**
//	 * 查询列表 菜单配置
//	 * @return T
//	 */
//	public T query(T idSysMenuEntry);
//	
//	public T queryPostByFid(T idSysMenuEntry);
//	
//	
//	/**
//	 * 添加 菜单配置
//	 * @return T
//	 */
//	@Transaction
//	public T add(T idSysMenuEntry);
//	
//	/**
//	 * 通过UUID更新 菜单配置
//	 * @return T
//	 */
//	@Transaction
//	public T update(T idSysMenuEntry);
//	
//	/**
//	 * 通过UUID删除 菜单配置
//	 * @return T
//	 */
//	@Transaction
//	public T del(T idSysMenuEntry);
//
//	/**
//	 * 获取菜单树
//	 * @param idSysMenuEntry
//	 * @return
//	 */
//	@Transaction
//	public T tree(T  idSysMenuEntry);
//	
	/**
	 * 获取拥有的菜单
	 * @param idSysMenuEntry
	 * @return
	 */
	public List<T> hasMenu(T entry);
//	
//	@Transaction
//	public T  selectMenu(T idSysMenuEntry);
//	@Transaction
//	public T  bindMenu(T idSysMenuEntry);
	
}