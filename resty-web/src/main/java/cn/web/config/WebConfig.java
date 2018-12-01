package cn.web.config;

import cn.base.config.RestyConfigExt;
import cn.base.interceptor.TokenInterceptor;
import cn.dreampie.route.config.ConstantLoader;
import cn.dreampie.route.config.HandlerLoader;
import cn.dreampie.route.config.InterceptorLoader;
import cn.dreampie.route.config.PluginLoader;
import cn.dreampie.route.config.ResourceLoader;
import cn.dreampie.route.interceptor.security.SecurityInterceptor;
import cn.sys.resource.service.SysAuthenticateService;

/**
 * Created by ice on 14-12-29.
 */
public class WebConfig extends RestyConfigExt {

	@Override
	public void configMoreConstants(ConstantLoader constantLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configMoreResource(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configMorePlugins(PluginLoader pluginLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configMoreInterceptors(InterceptorLoader interceptorLoader) {
		// TODO Auto-generated method stub
		// 权限拦截器
		 interceptorLoader.add(new SecurityInterceptor(new
				 SysAuthenticateService()));
		 interceptorLoader.add(new TokenInterceptor());
	}

	@Override
	public void configMoreHandlers(HandlerLoader handlerLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterStartMore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeStopMore() {
		// TODO Auto-generated method stub
		
	}

}
