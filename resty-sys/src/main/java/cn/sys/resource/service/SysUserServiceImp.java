package cn.sys.resource.service;

import cn.dreampie.log.Logger;
import cn.sys.resource.model.SysUser;

/**
 * 系统模块 菜单配置 服务类接口定义
 * 代码生成，文件存在则不重新生成
 * @author IDORP CODE GEN
 */
public class SysUserServiceImp implements ISysUserService<SysUser> {
	private static Logger LOG = Logger.getLogger(SysUserServiceImp.class);

	@Override
	public SysUser list(SysUser user) {
		SysUser.dao.list(user);
		return user;
	}
	
}