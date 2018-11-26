package cn.sys.resource.api;

import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.DELETE;
import cn.dreampie.route.annotation.POST;
import cn.dreampie.security.Subject;
import cn.sys.resource.SysResource;
import cn.sys.resource.api.validator.SigninValidator;
import cn.sys.resource.model.SysUser;

/**
 * Created by wangrenhui on 15/1/10.
 */
@API("/sessions")
public class SessionResource extends SysResource {


  @POST(des = "用户登录", valid = SigninValidator.class)
  public SysUser login(String account, String password, boolean remember) {
    Subject.login(account, password);
    return (SysUser) Subject.getPrincipal().getModel();
  }

  @DELETE(des = "用户退出")
  public void logout() {
    Subject.logout();
  }
}
