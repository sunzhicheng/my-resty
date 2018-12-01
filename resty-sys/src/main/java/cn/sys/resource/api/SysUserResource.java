package cn.sys.resource.api;

import java.util.List;

import cn.base.tool.ToolException;
import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.POST;
import cn.sys.resource.SysApiResource;
import cn.sys.resource.model.SysUser;

/**
 * Created by wangrenhui on 15/1/10.
 */
@API(value="/user",isOpen=true)
public class SysUserResource extends SysApiResource {
	private static final Logger LOG = Logger.getLogger(SysUserResource.class);
	@POST(des = "用户列表",value="/list")
	public List<SysUser> list() {
		throw ToolException.getEx("异常啦!!!!");
//		return new ArrayList<>();
	}
	@POST(des = "新增用户",value="/add")
	public void add() {
	}
	@POST(des = "修改用户",value="/update")
	public void update() {
	}
}
