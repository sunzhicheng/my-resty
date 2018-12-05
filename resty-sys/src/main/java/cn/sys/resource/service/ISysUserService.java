package cn.sys.resource.service;

import cn.sys.resource.model.SysUser;

/**
 * 系统模块 菜单配置 服务类接口定义
 * 代码生成，文件存在则不重新生成
 * @author IDORP CODE GEN
 */
public interface ISysUserService<T extends SysUser> {
	
	public T list(SysUser menu);
	
}