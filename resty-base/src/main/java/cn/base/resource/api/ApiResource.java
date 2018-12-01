package cn.base.resource.api;

import cn.dreampie.log.Logger;
import cn.dreampie.route.annotation.API;
import cn.dreampie.route.core.Resource;

/**
 * API 路径规则
 * /API固定前缀/版本号/模块名称/功能名称/方法名称/:参数名称
 * @author sunzc
 *
 */
@API("/api/v1.0")
public class ApiResource  extends Resource {
	private static final Logger LOG = Logger.getLogger(ApiResource.class);
}
	
