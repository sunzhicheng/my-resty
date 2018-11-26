package cn.sys.resource.api.validator;

import cn.dreampie.common.util.matcher.PatternMatcher;
import cn.dreampie.route.core.Params;
import cn.dreampie.route.core.RouteMatch;
import cn.dreampie.route.valid.ValidResult;
import cn.dreampie.route.valid.Validator;

/**
 * Created by ice on 15-1-26.
 */
public class SigninValidator extends Validator {

  public ValidResult validate(Params params, RouteMatch routeMatch) {
    ValidResult result = new ValidResult();
    String account = params.get("account");
    if (account == null || !PatternMatcher.isGeneral(account, 5, 16))
      result.addError("account", "用户名错误!");
    return result;
  }
}
