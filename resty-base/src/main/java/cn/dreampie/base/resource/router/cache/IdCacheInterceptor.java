package cn.dreampie.base.resource.router.cache;

import cn.dreampie.cache.SimpleCache;
import cn.dreampie.common.Constant;
import cn.dreampie.common.http.HttpMethod;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.HttpResponse;
import cn.dreampie.common.http.result.HttpStatus;
import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.annotation.GET;
import cn.dreampie.route.core.RouteInvocation;
import cn.dreampie.route.core.RouteMatch;
import cn.dreampie.route.interceptor.Interceptor;

import java.util.Date;
import java.util.UUID;

/**
 * API 请求缓存拦截器
 * 
 * @author haoyin
 *
 */
public class IdCacheInterceptor implements Interceptor {

	private final static Logger logger = Logger.getLogger(IdCacheInterceptor.class);
	public static final String HTTP_DEF_KEY = "_http";
	private int expired = -1;// -1 表示事件驱动缓存更新 缓存不会过期

	public IdCacheInterceptor() {

	}

	public IdCacheInterceptor(int expired) {
		this.expired = expired;
	}

	public void intercept(RouteInvocation ri) {
		logger.debug("intercept cacheEnabled : " + Constant.cacheEnabled);
		if (Constant.cacheEnabled) {
			@SuppressWarnings("unchecked")
			API apiAnno = (API) ri.getResourceClass().getAnnotation(API.class);
			String apiValue = apiAnno.value();
			RouteMatch routeMatch = ri.getRouteMatch();
			HttpRequest request = routeMatch.getRequest();
			HttpResponse response = routeMatch.getResponse();
			String method = request.getHttpMethod();
			String group = HTTP_DEF_KEY + Constant.CONNECTOR + apiValue;
			String uri = request.getRestUri();

			String version = SimpleCache.instance().get(group, apiValue);

			if (method.equals(HttpMethod.GET)) {
				GET getAnno = ri.getMethod().getAnnotation(GET.class);

				// 缓存请求判断
				if (getAnno.cached()) {
					String previousToken = request.getHeader("If-None-Match");

					// compare previous token with current one
					if ((version != null) && (previousToken != null && previousToken.equals('"' + version + '"'))) {
						HttpStatus status = HttpStatus.NOT_MODIFIED;
						response.setStatus(status);
						// re-use original last modified timestamp
						response.setHeader("Last-Modified", request.getHeader("If-Modified-Since"));
						logger.info("Not modify '" + uri + "'");
						return; // no further processing required
					}
//					int getCacheExpired = getAnno.expired();
					int getCacheExpired =10000;
					// 设置ETag
					setETag(apiValue, response, group, version, getCacheExpired);

					Object result = SimpleCache.instance().get(group, uri);
					if (result == null) {
						result = ri.invoke();
						SimpleCache.instance().add(group, uri, result, getCacheExpired);
					} else {
						// not invoke render cache result
						ri.render(result);
					}
					return;
				}
			} else {
				// 重置ETag
				setETag(apiValue, response, group, null, expired);
				SimpleCache.instance().flush(group);// 清楚 get 缓存
			}
		}
		// 不缓存请求
		ri.invoke();
	}

	private void setETag(String apiValue, HttpResponse response, String group, String version, int exp) {
		// set header for the next time the client calls
		if (version == null) {
			version = UUID.randomUUID().toString();
			SimpleCache.instance().add(group, apiValue, version, exp);
		}
		// first time through - set last modified time to now
		response.setHeader("Last-Modified", new Date().toString());
		response.setHeader("ETag", '"' + version + '"');
	}
}
