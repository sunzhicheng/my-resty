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
@API("/api/v1")
public class ApiResource  extends Resource {
	
	private static final Logger LOG = Logger.getLogger(ApiResource.class);
  /**
   * 基础的api Resource 用来添加基础的路径或版本号 和一些公用方法
   */
	
	
}
