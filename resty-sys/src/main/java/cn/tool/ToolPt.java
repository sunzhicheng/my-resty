package cn.tool;

import cn.base.tool.ToolException;
import cn.dreampie.security.Subject;
import cn.sys.resource.model.SysUser;

public class ToolPt {
	public static SysUser me() {
		if(Subject.getPrincipal()!= null && Subject.getPrincipal().getModel() != null) {
			return (SysUser)Subject.getPrincipal().getModel();
		}else {
			throw ToolException.getEx("非法操作:未登录用户");
		}
	}
}
