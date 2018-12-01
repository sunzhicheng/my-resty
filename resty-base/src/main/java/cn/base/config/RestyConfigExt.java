package cn.base.config;

import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.base.constant.ExtConstant;
import cn.base.interceptor.TokenInterceptor;
import cn.base.render.MyJsonRender;
import cn.dreampie.cache.CacheEvent;
import cn.dreampie.common.Constant;
import cn.dreampie.common.util.Stringer;
import cn.dreampie.log.Colorer;
import cn.dreampie.log.Logger;
import cn.dreampie.orm.ActiveRecordPlugin;
import cn.dreampie.orm.cache.QueryCache;
import cn.dreampie.orm.provider.c3p0.C3p0DataSourceProvider;
import cn.dreampie.route.cache.CacheInterceptor;
import cn.dreampie.route.config.Config;
import cn.dreampie.route.config.ConstantLoader;
import cn.dreampie.route.config.HandlerLoader;
import cn.dreampie.route.config.InterceptorLoader;
import cn.dreampie.route.config.PluginLoader;
import cn.dreampie.route.config.ResourceLoader;
import cn.dreampie.route.handler.cors.CORSHandler;
import cn.dreampie.route.interceptor.transaction.TransactionInterceptor;
import cn.dreampie.route.render.RenderFactory;

/**
 * Created by ice on 14-12-29.
 */
public abstract class RestyConfigExt extends Config {
	private final static Logger logger = Logger.getLogger(RestyConfigExt.class);
	
	/**
	 * Config other More constant
	 */
	public abstract void configMoreConstants(ConstantLoader constantLoader);
	
	/**
	 * Config other more route
	 */
	public abstract void configMoreResource(ResourceLoader resourceLoader);
	
	/**
	 * Config other more plugin
	 */
	public abstract void configMorePlugins(PluginLoader pluginLoader);
	
	/**
	 * Config other more interceptor applied to all actions.
	 */
	public abstract void configMoreInterceptors(InterceptorLoader interceptorLoader);
	
	/**
	 * Config other more handler
	 */
	public abstract void configMoreHandlers(HandlerLoader handlerLoader);
	
	
	public abstract void afterStartMore();
	public abstract void beforeStopMore();
	
	
	
	
	public void configConstant(ConstantLoader constantLoader) {
		constantLoader.addJsonSerializerFeature(SerializerFeature.WriteNullStringAsEmpty);
		constantLoader.addRender(RenderFactory.JSON, new MyJsonRender());
		// 关系到日志输出，连接池关闭等
		Colorer.devEnable(false);
		configMoreConstants(constantLoader);
	}

	public void configResource(ResourceLoader resourceLoader) {
		// 设置resource的目录 减少启动扫描目录
		// resourceLoader.addExcludePackages("cn.dreampie.resource");
		// resourceLoader.addIncludePackages("cn.dreampie.resource");
		  if(Stringer.notBlank(ExtConstant.includePackages)) {
			  resourceLoader.addIncludePackages(ExtConstant.includePackages.split(","));
	      }
		configMoreResource(resourceLoader);
	}

	public void configPlugin(PluginLoader pluginLoader) {
		 C3p0DataSourceProvider cdsp = new C3p0DataSourceProvider(ExtConstant.app);
		 ActiveRecordPlugin activeRecordCdsp = new ActiveRecordPlugin(cdsp);
		  if(Stringer.notBlank(ExtConstant.includePackages)) {
	    	  activeRecordCdsp.addIncludePackages(ExtConstant.includePackages.split(","));
	      }
		 pluginLoader.add(activeRecordCdsp);
		
		
		// //第一个数据库
		// C3p0DataSourceProvider cdsp = new C3p0DataSourceProvider("default");
		// ActiveRecordPlugin activeRecordCdsp = new ActiveRecordPlugin(cdsp);
		// activeRecordCdsp.addIncludePackages("cn.dreampie.resource");
		// pluginLoader.add(activeRecordCdsp);
		////
		//// //第二个数据库
		// DruidDataSourceProvider ddsp = new DruidDataSourceProvider("demo");
		// ActiveRecordPlugin activeRecordDdsp = new ActiveRecordPlugin(ddsp);
		//// activeRecordDdsp.addIncludePackages("cn.dreampie.demo"); 只是测试
		// 没有具体的包有该数据源的对象
		// pluginLoader.add(activeRecordDdsp);

		// 读写分离
		// DruidDataSourceProvider writeDsp = new
		// DruidDataSourceProvider("write");
		// DruidDataSourceProvider readDsp = new
		// DruidDataSourceProvider("read");
		// ActiveRecordPlugin activeRecordDdsp = new
		// ActiveRecordPlugin("readwrite", writeDsp, readDsp);
		// activeRecordDdsp.addIncludePackages("cn.dreampie.resource");
		// pluginLoader.add(activeRecordDdsp);

		// pluginLoader.add(new SpringPlugin(HelloApp.class));
		// JndiDataSourceProvider jdsp = new JndiDataSourceProvider("jndiDs",
		// "jndiName");
		// ActiveRecordPlugin activeRecordJdsp = new ActiveRecordPlugin(jdsp);
		// pluginLoader.add(activeRecordJdsp);
		
		// 启动Quartz任务插件
//		if (idorpConf.hasQuartzPlugin()) {
//			pluginLoader.add(new QuartzPlugin(idorpConf.getQuartzPluginBuilder().getQuartzDsName()));
//		}
		 
		 configMorePlugins(pluginLoader);
	}

	public void configInterceptor(InterceptorLoader interceptorLoader) {
		// //事务的拦截器 @Transaction
		 interceptorLoader.add(new TransactionInterceptor());
		 
		//GET请求缓存
		interceptorLoader.add(new CacheInterceptor());
		 configMoreInterceptors(interceptorLoader);
	}

	public void configHandler(HandlerLoader handlerLoader) {
		// 跨域
		 handlerLoader.add(new CORSHandler("GET,POST,PUT,DELETE"));
		 
		 configMoreHandlers(handlerLoader);
	}
	
	/**
	   * Call back after Resty start
	   */
	  public void afterStart() {
		// 根据配置删除所有缓存数据库缓存
		if (ExtConstant.delQueryCache && Constant.cacheEnabled) {
			QueryCache.instance().getCacheProvider().doFlush(new CacheEvent(QueryCache.QUERY_DEF_KEY+"*",getClass().getName()));
		}
		 afterStartMore();
	  }

	  /**
	   * Call back before Resty stop
	   */
	  public void beforeStop() {
		  beforeStopMore();
	  }
	
	
}
