package cn.sys.resource.service;

import java.util.HashSet;
import java.util.Set;

import cn.base.tool.ToolException;
import cn.dreampie.common.http.HttpMessage;
import cn.dreampie.common.http.exception.HttpException;
import cn.dreampie.security.AuthenticateService;
import cn.dreampie.security.DefaultPasswordService;
import cn.dreampie.security.PasswordService;
import cn.dreampie.security.Principal;
import cn.dreampie.security.credential.Credential;
import cn.sys.resource.model.SysUser;

/**
 * Created by ice on 15-1-7.
 */
public class SysAuthenticateService extends AuthenticateService {

  /**
	 * 
	 */
	private static final long serialVersionUID = -2029769653968185575L;

public PasswordService getPasswordService() {
    return super.getPasswordService();
  }

  /**
   * 查询用户信息  这儿new一个用户对象来模拟
   *
   * @param account 登录的用户名
   * @return 用户权限对象
   */
  public Principal getPrincipal(String account) {
    SysUser user = SysUser.dao.findFirstBy(" (account=? or email=? or mobile=?) and sql_status=1  ", account,account,account);
    if(user != null) {
    	user.put("permissions", new HashSet<String>() {{
    	      add("users");
    	    }});
    	    Principal<SysUser> principal = new Principal<SysUser>(account, user.getPassword(), user.getSalt(), new HashSet<String>() {{
    	        add("users");}}, user);
    	    return principal;
    }else {
    	throw ToolException.getEx("账户错误");
    }
    
  }

  /**
   * 加载全部的权限信息
   *
   * @return 权限集合
   */
  public Set<Credential> getAllCredentials() {
    Set<Credential> credentials = new HashSet<Credential>();
    credentials.add(new Credential("*", "/api/v1.0/users/**", "users"));
    return credentials;
  }
  
  public static void main(String[] args) {
	  PasswordService passwordService = DefaultPasswordService.instance();
	  String salt = "password_salt";
	  System.out.println(passwordService.crypto("123456",salt));
	  
  }
}
