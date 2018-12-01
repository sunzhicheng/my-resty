package cn.base.interceptor;

import cn.dreampie.common.http.HttpMessage;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.HttpResponse;
import cn.dreampie.common.http.exception.HttpException;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.core.RouteInvocation;
import cn.dreampie.route.core.RouteMatch;
import cn.dreampie.route.interceptor.Interceptor;
import cn.dreampie.security.Principal;
import cn.dreampie.security.Subject;

/**
 * Created by sunzhicheng on 18/11/18.
 */
public class TokenInterceptor implements Interceptor {

  public void intercept(RouteInvocation ri) {
	  API apiAnno = (API) ri.getResourceClass().getAnnotation(API.class);
      boolean isOpen = apiAnno.isOpen();
      RouteMatch routeMatch = ri.getRouteMatch();
      HttpRequest request = routeMatch.getRequest();
      HttpResponse response = routeMatch.getResponse();
      String method = request.getHttpMethod();
      if(!isOpen) {  //如果不是开放模式  是不允许anonymous  用户访问的
    	  Principal p = Subject.getPrincipal();
    	  if(p == null) {
    		  throw new HttpException(HttpMessage.UNAUTHORIZED);
    	  }
      }
    ri.invoke();
  }

}
