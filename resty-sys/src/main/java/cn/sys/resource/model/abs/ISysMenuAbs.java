package cn.sys.resource.model.abs;

import cn.dreampie.orm.Model;
import cn.sys.resource.model.PlatformRestyModel;

/**
 * 系统模块 菜单配置 抽像类
 * 此类会强制覆盖更新，不要做任何手动修改
 * @author Idorp Code Gen
 */
@SuppressWarnings("unused")
public abstract class ISysMenuAbs<M extends Model<M>> extends PlatformRestyModel<M> {
	
	/**
	 * 除公共列以外的其它列
	 */
	public String[] EXT_CLOS = new String [] {
			COL_PARENT_ID,
			COL_PARENT_IDS,
			COL_NAME,
			COL_SORT,
			COL_HREF,
			COL_TARGET,
			COL_ICON,
			COL_IS_SHOW,
			COL_PERMISSION,
			COL_REMARKS,
			COL_MENU_TYPE,
			COL_API_URL,
			COL_ADMIN_SHOW,
	};
	
	//columns START
	/**
	 * 	字段描述:	菜单配置
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_ID = "id";  
	/**
	 * 	字段描述:	唯一UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_UUID = "uuid";  
	/**
	 * 	字段描述:	父级编号
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_PARENT_ID = "parent_id";  
	/**
	 * 	字段描述:	所有父级编号
	 * 	字段类型:	TEXT  
	 */
    public static final String COL_PARENT_IDS = "parent_ids";  
	/**
	 * 	字段描述:	名称
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_NAME = "name";  
	/**
	 * 	字段描述:	排序
	 * 	字段类型:	INT  
	 */
    public static final String COL_SORT = "sort";  
	/**
	 * 	字段描述:	链接
	 * 	字段类型:	TEXT  
	 */
    public static final String COL_HREF = "href";  
	/**
	 * 	字段描述:	目标
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_TARGET = "target";  
	/**
	 * 	字段描述:	图标
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_ICON = "icon";  
	/**
	 * 	字段描述:	是否在菜单中显示 1:显示 2：不显示
	 * 	字段类型:	INT  
	 */
    public static final String COL_IS_SHOW = "is_show";  
	/**
	 * 	字段描述:	权限接口 多个用”,”英文逗号隔开
	 * 	字段类型:	TEXT  
	 */
    public static final String COL_PERMISSION = "permission";  
	/**
	 * 	字段描述:	备注
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_REMARKS = "remarks";  
	/**
	 * 	字段描述:	菜单类型 1：pc 2：app 3：微信端 
	 * 	字段类型:	INT  
	 */
    public static final String COL_MENU_TYPE = "menu_type";  
	/**
	 * 	字段描述:	api地址
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_API_URL = "api_url";  
	/**
	 * 	字段描述:	更新的时候版本控制
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_VERSION = "version";  
	/**
	 * 	字段描述:	创建者UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_CREATE_UUID = "create_uuid";  
	/**
	 * 	字段描述:	更新者UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_UPDATE_UUID = "update_uuid";  
	/**
	 * 	字段描述:	禁用者UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_DISABLE_UUID = "disable_uuid";  
	/**
	 * 	字段描述:	删除者UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_DEL_UUID = "del_uuid";  
	/**
	 * 	字段描述:	创建时间
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_CREATE_AT = "create_at";  
	/**
	 * 	字段描述:	更新时间
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_UPDATE_AT = "update_at";  
	/**
	 * 	字段描述:	禁用时间
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_DISABLE_AT = "disable_at";  
	/**
	 * 	字段描述:	删除时间
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_DEL_AT = "del_at";  
	/**
	 * 	字段描述:	数据状态 0. 删除， 1. 正常, 2. 禁用
	 * 	字段类型:	INT  
	 */
    public static final String COL_SQL_STATUS = "sql_status";  
	/**
	 * 	字段描述:	业务状态,由务业务表根据情况定义, 1. 新建
	 * 	字段类型:	INT  
	 */
    public static final String COL_STATE = "state";  
	/**
	 * 	字段描述:	应用ID,数据库所有数据需要属于一个应用,channel_id就是应用ID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_CHANNEL_ID = "channel_id";  
	/**
	 * 	字段描述:	是否超级管理员显示 1：显示 2：不显示
	 * 	字段类型:	INT  
	 */
    public static final String COL_ADMIN_SHOW = "admin_show";  
	
    public ISysMenuAbs<M> setid(java.lang.Long value) {
        set(COL_ID, value);
        return this;
    }

	public java.lang.Long getId() {
		return getLong(COL_ID);
	}
    public ISysMenuAbs<M> setUuid(java.lang.String value) {
        set(COL_UUID, value);
        return this;
    }

	public java.lang.String getUuid() {
		return getStr(COL_UUID);
	}
    public ISysMenuAbs<M> setParentId(java.lang.String value) {
        set(COL_PARENT_ID, value);
        return this;
    }

	public java.lang.String getParentId() {
		return get(COL_PARENT_ID);
	}
    public ISysMenuAbs<M> setParentIds(java.lang.String value) {
        set(COL_PARENT_IDS, value);
        return this;
    }

	public java.lang.String getParentIds() {
		return get(COL_PARENT_IDS);
	}
    public ISysMenuAbs<M> setName(java.lang.String value) {
        set(COL_NAME, value);
        return this;
    }

	public java.lang.String getName() {
		return get(COL_NAME);
	}
    public ISysMenuAbs<M> setSort(java.lang.Integer value) {
        set(COL_SORT, value);
        return this;
    }

	public java.lang.Integer getSort() {
		return get(COL_SORT);
	}
    public ISysMenuAbs<M> setHref(java.lang.String value) {
        set(COL_HREF, value);
        return this;
    }

	public java.lang.String getHref() {
		return get(COL_HREF);
	}
    public ISysMenuAbs<M> setTarget(java.lang.String value) {
        set(COL_TARGET, value);
        return this;
    }

	public java.lang.String getTarget() {
		return get(COL_TARGET);
	}
    public ISysMenuAbs<M> setIcon(java.lang.String value) {
        set(COL_ICON, value);
        return this;
    }

	public java.lang.String getIcon() {
		return get(COL_ICON);
	}
    public ISysMenuAbs<M> setIsShow(java.lang.Integer value) {
        set(COL_IS_SHOW, value);
        return this;
    }

	public java.lang.Integer getIsShow() {
		return get(COL_IS_SHOW);
	}
    public ISysMenuAbs<M> setPermission(java.lang.String value) {
        set(COL_PERMISSION, value);
        return this;
    }

	public java.lang.String getPermission() {
		return get(COL_PERMISSION);
	}
    public ISysMenuAbs<M> setRemarks(java.lang.String value) {
        set(COL_REMARKS, value);
        return this;
    }

	public java.lang.String getRemarks() {
		return get(COL_REMARKS);
	}
    public ISysMenuAbs<M> setMenuType(java.lang.Integer value) {
        set(COL_MENU_TYPE, value);
        return this;
    }

	public java.lang.Integer getMenuType() {
		return get(COL_MENU_TYPE);
	}
    public ISysMenuAbs<M> setApiUrl(java.lang.String value) {
        set(COL_API_URL, value);
        return this;
    }

	public java.lang.String getApiUrl() {
		return get(COL_API_URL);
	}
    public ISysMenuAbs<M> setVersion(java.lang.Long value) {
        set(COL_VERSION, value);
        return this;
    }

	public java.lang.Long getVersion() {
		return get(COL_VERSION);
	}
    public ISysMenuAbs<M> setCreateUuid(java.lang.String value) {
        set(COL_CREATE_UUID, value);
        return this;
    }

	public java.lang.String getCreateUuid() {
		return get(COL_CREATE_UUID);
	}
    public ISysMenuAbs<M> setUpdateUuid(java.lang.String value) {
        set(COL_UPDATE_UUID, value);
        return this;
    }

	public java.lang.String getUpdateUuid() {
		return get(COL_UPDATE_UUID);
	}
    public ISysMenuAbs<M> setDisableUuid(java.lang.String value) {
        set(COL_DISABLE_UUID, value);
        return this;
    }

	public java.lang.String getDisableUuid() {
		return get(COL_DISABLE_UUID);
	}
    public ISysMenuAbs<M> setDelUuid(java.lang.String value) {
        set(COL_DEL_UUID, value);
        return this;
    }

	public java.lang.String getDelUuid() {
		return get(COL_DEL_UUID);
	}
    public ISysMenuAbs<M> setCreateAt(java.lang.Long value) {
        set(COL_CREATE_AT, value);
        return this;
    }

	public java.lang.Long getCreateAt() {
		return get(COL_CREATE_AT);
	}
    public ISysMenuAbs<M> setUpdateAt(java.lang.Long value) {
        set(COL_UPDATE_AT, value);
        return this;
    }

	public java.lang.Long getUpdateAt() {
		return get(COL_UPDATE_AT);
	}
    public ISysMenuAbs<M> setDisableAt(java.lang.Long value) {
        set(COL_DISABLE_AT, value);
        return this;
    }

	public java.lang.Long getDisableAt() {
		return get(COL_DISABLE_AT);
	}
    public ISysMenuAbs<M> setDelAt(java.lang.Long value) {
        set(COL_DEL_AT, value);
        return this;
    }

	public java.lang.Long getDelAt() {
		return get(COL_DEL_AT);
	}
    public ISysMenuAbs<M> setSqlStatus(java.lang.Integer value) {
        set(COL_SQL_STATUS, value);
        return this;
    }

	public java.lang.Integer getSqlStatus() {
		return get(COL_SQL_STATUS);
	}
    public ISysMenuAbs<M> setState(java.lang.Integer value) {
        set(COL_STATE, value);
        return this;
    }

	public java.lang.Integer getState() {
		return get(COL_STATE);
	}
    public ISysMenuAbs<M> setChannelId(java.lang.String value) {
        set(COL_CHANNEL_ID, value);
        return this;
    }

	public java.lang.String getChannelId() {
		return get(COL_CHANNEL_ID);
	}
    public ISysMenuAbs<M> setAdminShow(java.lang.Integer value) {
        set(COL_ADMIN_SHOW, value);
        return this;
    }

	public java.lang.Integer getAdminShow() {
		return get(COL_ADMIN_SHOW);
	}
}