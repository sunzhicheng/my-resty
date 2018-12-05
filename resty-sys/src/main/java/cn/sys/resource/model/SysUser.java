package cn.sys.resource.model;

import java.util.ArrayList;
import java.util.List;

import cn.base.resource.model.Pager;
import cn.base.resource.model.Query;
import cn.base.tool.ToolString;
import cn.dreampie.orm.annotation.Table;
import cn.sys.resource.model.abs.ISysUser;
/**
 * Created by ice on 14-12-31.
 */
//@JSONType(parseFeatures = {Feature.IgnoreNotMatch})
@Table(name = "sys_user",  primaryKey = "id",  cached = true)
public class SysUser extends ISysUser<SysUser> {
  public static SysUser dao = new SysUser();

  public static final String[] list_head = {"ID","账户","名称","手机号码","邮箱"};
  public static final String[] list_col = { COL_ID, COL_ACCOUNT, COL_NICKNAME, COL_MOBILE, COL_EMAIL};
	public static final HEAD_TYPE[] list_type = { HEAD_TYPE.Long, HEAD_TYPE.String, HEAD_TYPE.String, HEAD_TYPE.String,HEAD_TYPE.String};

  public static final String list = "select * from sys_user ";
  public void list(SysUser entry){
	  Query query = entry.getQuery();
	  String nickName = query.getItem("nickname");
	  List<String> parmList = new ArrayList<>();
	  StringBuilder sql = new StringBuilder(list);
	  if(ToolString.isNotNull(nickName)) {
		  sql.append(" where nickname=? ");
		  parmList.add(nickName);
	  }
	  entry.getPager().setPageHead(list_head, list_col, list_type);
	  Pager pager = splitPage(entry.getPager(), sql.toString(),parmList.toArray());
  }
  
  
  // 默认 getXxx 的形式的方法 会被认为是属性 如果userInfos的值不存在 方法会被执行一次
  // json反转时  如果 getXxx的存在  会按 getXxx的返回值类型 进行转换 如：{userInfos:[{key:value,key1:value1}]} userInfos会被转换为  List<UserInfo>类型
  //  @JSONField(serialize = false) 如果getXxx的值不转为json  使用该注解
  // 注意属性名和GetXxx一致   如:属性userInfos的get方法为 getUserInfos
  // 支持驼峰和下划线 两种属性名字和驼峰方法的映射 (userInfos也可以使用下划线模式 user_infos全小写 也会映射到getUserInfos()方法)
  // 个人喜欢数据库和属性 都使用下划线的方式
//  public List<UserInfo> getUserInfos() {
//    if (this.get("user_infos") == null && this.get("id") != null) {
//      this.put("user_infos", UserInfo.dao.findBy("user_id=?", this.get("id")));
//    }
//    return this.get("user_infos");
//  }

  public Long getRoleId() {
//    if (this.get("role_id") == null && this.get("id") != null) {
//      String sql = "SELECT user_role.role_id FROM sec_user_role user_role WHERE user_role.user_id=?";
//      this.put("role_id", queryFirst(sql, this.get("id")));
//    }
//    return this.get("role_id");
	  return 1L;
  }

  public List<String> getPermissions() {
//    Long roleId = getRoleId();
//    if (this.get("permissions") == null && roleId != null) {
//      String sql = "SELECT permission.value FROM sec_permission permission WHERE permission.id in(SELECT rolePermission.permission_id FROM sec_role_permission rolePermission WHERE rolePermission.role_id=?)";
//      this.put("permissions", query(sql, roleId));
//    }
//    return this.get("permissions");
	  return null;
  }

  public List<Long> getPermissionIds() {
//    Long roleId = getRoleId();
//    if (this.get("permission_ids") == null && roleId != null) {
//      String sql = "SELECT permission.id FROM sec_permission permission WHERE permission.id in(SELECT rolePermission.permission_id FROM sec_role_permission rolePermission WHERE rolePermission.role_id=?)";
//      this.put("permission_ids", query(sql, roleId));
//    }
//    return this.get("permission_ids");
	  return null;
  }
}
