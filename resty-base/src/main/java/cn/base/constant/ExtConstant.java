package cn.base.constant;

import cn.dreampie.common.util.properties.Prop;
import cn.dreampie.common.util.properties.Proper;
import cn.dreampie.log.Logger;

/**
 * Created by ice on 14-12-29.
 */
public final class ExtConstant {

  public final static String app ;
  public final static boolean delQueryCache;
  public final static  String  includePackages;
  

  private final static Logger logger = Logger.getLogger(ExtConstant.class);

  static {
    Prop constants = null;
    try {
      constants = Proper.use("ext.properties");
    } catch (Exception e) {
      logger.warn(e.getMessage());
    }
    if (constants == null) {
    	app = "default";
    	delQueryCache = false;
    	includePackages= "";
    } else {
      app = constants.get("app.name", "default");
      delQueryCache = constants.getBoolean("del.query.cache.enabled", false);
      includePackages = constants.get("include.packages");
    }
  }
}
