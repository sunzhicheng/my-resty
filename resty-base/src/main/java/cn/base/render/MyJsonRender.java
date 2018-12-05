package cn.base.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.base.resource.model.RestyBaseModel;
import cn.dreampie.common.Constant;
import cn.dreampie.common.Render;
import cn.dreampie.common.http.ContentType;
import cn.dreampie.common.http.HttpRequest;
import cn.dreampie.common.http.HttpResponse;
import cn.dreampie.common.util.json.Jsoner;
import cn.dreampie.log.Logger;

/**
 * Created by ice on 14-12-29.
 *
 * @JsonerFiled(serialize=false)
 */
public class MyJsonRender extends Render {
	private static final Logger LOG = Logger.getLogger(MyJsonRender.class);
	public void render(HttpRequest request, HttpResponse response, Object out) {
		if (out != null) {
			response.setContentType(ContentType.JSON.value());
			if (out instanceof String) {
				if (Jsoner.isJson((String) out)) {
					write(request, response, (String) out);
				} else {
					write(request, response, "\"" + out + "\"");
				}
			} else {
				if (out instanceof RestyBaseModel) {
					out = buildRender(out);
				} else if (out instanceof ArrayList) {
					List list = (ArrayList) out;
					for (int i = 0; i < list.size(); i++) {
						Object obj = list.get(i);
						if (obj instanceof RestyBaseModel) {
							obj = buildRender(obj);
							list.set(i, obj);
						}
					}
				} else if (out instanceof HashMap) {
					HashMap map = (HashMap) out;
					for (Object key : map.keySet()) {
						Object obj = map.get(key);
						if (obj instanceof RestyBaseModel) {
							obj = buildRender(obj);
							map.put(key, obj);
						}
					}
				}
				String json = Jsoner.toJSON(out);
				if(Constant.devEnable) {
					LOG.debug("url= "+request.getRestPath()+"  返回  json  "+json);
				}
				write(request, response, json);
			}
		}
	}

	private Object buildRender(Object model) {
		if (model instanceof RestyBaseModel) {
			model = ((RestyBaseModel) model).buildRender();
		}
		return model;
	}

}
