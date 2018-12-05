package cn.base.interceptor;

import cn.base.resource.model.Pager;
import cn.dreampie.common.http.HttpMessage;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.HttpResponse;
import cn.dreampie.common.http.exception.HttpException;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.core.Params;
import cn.dreampie.route.core.RouteInvocation;
import cn.dreampie.route.core.RouteMatch;
import cn.dreampie.route.interceptor.Interceptor;
import cn.dreampie.security.Principal;
import cn.dreampie.security.Subject;

/**
 * Created by sunzhicheng on 18/11/18.
 * 匹配平台默认的属性  比如 pager
 */
public class ModelInterceptor implements Interceptor {

  public void intercept(RouteInvocation ri) {
	  RouteMatch routeMatch = ri.getRouteMatch();
      HttpRequest request = routeMatch.getRequest();
      HttpResponse response = routeMatch.getResponse();
      Params params = routeMatch.getParams();
      Pager pager = params.get("pager");
//      if(pager)
    ri.invoke();
  }

}
