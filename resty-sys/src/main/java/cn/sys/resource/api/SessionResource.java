package cn.sys.resource.api;

import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.security.Subject;
import cn.sys.resource.SysApiResource;
import cn.sys.resource.api.validator.SigninValidator;
import cn.sys.resource.model.SysUser;

/**
 * Created by wangrenhui on 15/1/10.
 */
@API(value="/sessions",isOpen=true)
public class SessionResource extends SysApiResource {
	private static final Logger LOG = Logger.getLogger(SessionResource.class);
	@POST(des = "用户登录", valid = SigninValidator.class)
	public SysUser login(SysUser entry) {
		Subject.login(entry.getAccount(), entry.getPassword(),true);
		return (SysUser) Subject.getPrincipal().getModel();
	}

	@DELETE(des = "用户退出")
	public void logout() {
		Subject.logout();
	}
}
