package cn.sys.resource.api;

import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.POST;
import cn.sys.resource.SysApiResource;
import cn.sys.resource.model.SysUser;
import cn.sys.resource.service.SysServiceFactory;

/**
 * Created by wangrenhui on 15/1/10.
 */
@API(value="/user")
public class SysUserResource extends SysApiResource {
	private static final Logger LOG = Logger.getLogger(SysUserResource.class);
	@POST(des = "用户列表",value="/query")
	public SysUser list(SysUser user) {
		return SysServiceFactory.getInstance().getSysUserService().list(user);
	}
	@POST(des = "新增用户",value="/add")
	public void add() {
	}
	@POST(des = "修改用户",value="/update")
	public void update() {
	}
}
