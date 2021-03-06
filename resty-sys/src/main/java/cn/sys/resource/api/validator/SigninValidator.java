package cn.sys.resource.api.validator;

import cn.base.validator.BaseValidator;
import cn.dreampie.common.util.matcher.PatternMatcher;
import cn.dreampie.route.core.Params;
import cn.dreampie.route.core.RouteMatch;
import cn.dreampie.route.valid.ValidResult;
import cn.sys.resource.model.SysUser;

/**
 * Created by ice on 15-1-26.
 */
public class SigninValidator extends BaseValidator<SysUser> {

  public ValidResult validate(Params params, RouteMatch routeMatch) {
    ValidResult result = new ValidResult();
    SysUser user = getModel(params);
    String account = user.getAccount();
    if (account == null || !PatternMatcher.isGeneral(account, 5, 16))
      result.addError("account", "用户名错误!");
    return result;
  }
}
