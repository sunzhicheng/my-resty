package cn.dreampie.base.resource.router.cache;

import cn.dreampie.log.Logger;
import cn.dreampie.route.interceptor.Interceptor;

/**
 * IDORP 路由缓存工具类
 * @author haoyin
 *
 */
public class IdRouterCacheTool {
	private static Logger LOG = Logger.getLogger(IdRouterCacheTool.class);
	
	/**
	 * 通过类名获取缓存拦截器
	 * @param className
	 * @return Interceptor
	 */
	public static Interceptor getCacheIterceptor(String className) {
		Interceptor inter = null;
		try {
	        Class<?> cacheIterceptorClass = Class.forName(className);
	        Object obj = cacheIterceptorClass.newInstance();
	        if (obj instanceof Interceptor) {
	        	inter = (Interceptor) obj;
	        }
	      } catch (ClassNotFoundException e) {
	    	  LOG.error("Could not found Idorp router cache Class.", e);
	      } catch (InstantiationException e) {
	    	  LOG.error("Could not init Idorp router cache Class.", e);
	      } catch (IllegalAccessException e) {
	    	  LOG.error("Could not access Idorp router cache Class.", e);
	      }	
		return inter;
	}

}
