package cn.base.validator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.base.resource.model.RestyBaseModel;
import cn.dreampie.route.core.Params;
import cn.dreampie.route.valid.Validator;

/**
 * Created by ice on 15-1-26.
 */
public abstract class BaseValidator<T extends RestyBaseModel> extends Validator {
	protected Class<T> clazz;
	/**
	 * 获取泛型类对像
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Class<T> getTClass() {
		if (this.clazz == null) {
			Class clazz = getClass();
			while (clazz != Object.class) {
				Type t = clazz.getGenericSuperclass();
				if (t instanceof ParameterizedType) {
					Type[] args = ((ParameterizedType) t).getActualTypeArguments();
					if (args[0] instanceof Class) {
						this.clazz = (Class<T>) args[0];
						break;
					}
				}
				clazz = clazz.getSuperclass();
			}
		}
		return this.clazz;
	}
	
	public T getModel(Params p) {
		Class  modelClass = this.getTClass();
		return (T) p.get("entry", modelClass);
	}
}
