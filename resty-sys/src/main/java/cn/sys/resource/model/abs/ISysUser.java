package cn.sys.resource.model.abs;

import cn.base.resource.model.RestyBaseModel;
import cn.base.tool.ToolString;
import cn.dreampie.orm.Model;

/**
 * 用户
 * @author ozj
 */
@SuppressWarnings("unused")
public  abstract  class ISysUser<M extends Model<M>>  extends RestyBaseModel<M> {

	
	//columns START
	/**
	 * 	字段描述:	用户id
	 * 	字段类型:	BIGINT  
	 */
    public static final String COL_ID = "id";  
	/**
	 * 	字段描述:	唯一UUID
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_UUID = "uuid";  
	/**
	 * 	字段描述:	用户名（真实姓名）
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_NICKNAME = "nickname";  
	/**
	 * 	字段描述:	手机号
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_MOBILE = "mobile";  
	/**
	 * 	字段描述:	账户
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_ACCOUNT = "account";  
	/**
	 * 	字段描述:	邮箱
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_EMAIL = "email";  
	/**
	 * 	字段描述:	登录密码
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_PASSWORD = "password";  
	/**
	 * 	字段描述:	加密字段
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_SALT = "salt";  
	/**
	 * 	字段描述:	企业信息id
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_COMPANY_UUID = "company_uuid";  
	/**
	 * 	字段描述:	账号类型（预留） 1：主（超级账号） 2：副（可能下级员工）
	 * 	字段类型:	INT  
	 */
    public static final String COL_ACCOUNT_TYPE = "account_type";  
	/**
	 * 	字段描述:	用户平台 1.运营平台  2.商户平台
	 * 	字段类型:	INT  
	 */
    public static final String COL_USER_PT = "user_pt";  
	/**
	 * 	字段描述:	用户头像 uuid
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_HEAD_URL = "head_url";  
	/**
	 * 	字段描述:	主账号
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_SUPER_UUID = "super_uuid";  
	/**
	 * 	字段描述:	性别 1.男 2.女
	 * 	字段类型:	INT  
	 */
    public static final String COL_SEX = "sex";  
	/**
	 * 	字段描述:	是否认证 1.未认证 2.已认证已认证情况下不允许修改
	 * 	字段类型:	INT  
	 */
    public static final String COL_IS_AUTH = "is_auth";  
	/**
	 * 	字段描述:	身份证号
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_CARD_NUMBER = "card_number";  
	/**
	 * 	字段描述:	所属角色
	 * 	字段类型:	VARCHAR  
	 */
    public static final String COL_ROLE_UUID = "role_uuid";  
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
	
    public ISysUser setId(java.lang.Long value) {
    		if(value != 0){
    			set(COL_ID, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getId() {
			return getLong(COL_ID);
	}
    public ISysUser setUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getUuid() {
			return getStr(COL_UUID);
	}
    public ISysUser setNickname(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_NICKNAME, value);
    		}
        return this;
    }
    
 

	public java.lang.String getNickname() {
			return getStr(COL_NICKNAME);
	}
    public ISysUser setMobile(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_MOBILE, value);
    		}
        return this;
    }
    
 

	public java.lang.String getMobile() {
			return getStr(COL_MOBILE);
	}
    public ISysUser setAccount(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_ACCOUNT, value);
    		}
        return this;
    }
    
 

	public java.lang.String getAccount() {
			return getStr(COL_ACCOUNT);
	}
    public ISysUser setEmail(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_EMAIL, value);
    		}
        return this;
    }
    
 

	public java.lang.String getEmail() {
			return getStr(COL_EMAIL);
	}
    public ISysUser setPassword(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_PASSWORD, value);
    		}
        return this;
    }
    
 

	public java.lang.String getPassword() {
			return getStr(COL_PASSWORD);
	}
    public ISysUser setSalt(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_SALT, value);
    		}
        return this;
    }
    
 

	public java.lang.String getSalt() {
			return getStr(COL_SALT);
	}
    public ISysUser setCompanyUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_COMPANY_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getCompanyUuid() {
			return getStr(COL_COMPANY_UUID);
	}
    public ISysUser setAccountType(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_ACCOUNT_TYPE, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getAccountType() {
			return getInt(COL_ACCOUNT_TYPE);
	}
    public ISysUser setUserPt(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_USER_PT, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getUserPt() {
			return getInt(COL_USER_PT);
	}
    public ISysUser setHeadUrl(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_HEAD_URL, value);
    		}
        return this;
    }
    
 

	public java.lang.String getHeadUrl() {
			return getStr(COL_HEAD_URL);
	}
    public ISysUser setSuperUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_SUPER_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getSuperUuid() {
			return getStr(COL_SUPER_UUID);
	}
    public ISysUser setSex(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_SEX, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getSex() {
			return getInt(COL_SEX);
	}
    public ISysUser setIsAuth(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_IS_AUTH, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getIsAuth() {
			return getInt(COL_IS_AUTH);
	}
    public ISysUser setCardNumber(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_CARD_NUMBER, value);
    		}
        return this;
    }
    
 

	public java.lang.String getCardNumber() {
			return getStr(COL_CARD_NUMBER);
	}
    public ISysUser setRoleUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_ROLE_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getRoleUuid() {
			return getStr(COL_ROLE_UUID);
	}
    public ISysUser setVersion(java.lang.Long value) {
    		if(value != 0){
    			set(COL_VERSION, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getVersion() {
			return getLong(COL_VERSION);
	}
    public ISysUser setCreateUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_CREATE_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getCreateUuid() {
			return getStr(COL_CREATE_UUID);
	}
    public ISysUser setUpdateUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_UPDATE_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getUpdateUuid() {
			return getStr(COL_UPDATE_UUID);
	}
    public ISysUser setDisableUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_DISABLE_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getDisableUuid() {
			return getStr(COL_DISABLE_UUID);
	}
    public ISysUser setDelUuid(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_DEL_UUID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getDelUuid() {
			return getStr(COL_DEL_UUID);
	}
    public ISysUser setCreateAt(java.lang.Long value) {
    		if(value != 0){
    			set(COL_CREATE_AT, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getCreateAt() {
			return getLong(COL_CREATE_AT);
	}
    public ISysUser setUpdateAt(java.lang.Long value) {
    		if(value != 0){
    			set(COL_UPDATE_AT, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getUpdateAt() {
			return getLong(COL_UPDATE_AT);
	}
    public ISysUser setDisableAt(java.lang.Long value) {
    		if(value != 0){
    			set(COL_DISABLE_AT, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getDisableAt() {
			return getLong(COL_DISABLE_AT);
	}
    public ISysUser setDelAt(java.lang.Long value) {
    		if(value != 0){
    			set(COL_DEL_AT, value);
    		}
        return this;
    }
    
 

	public java.lang.Long getDelAt() {
			return getLong(COL_DEL_AT);
	}
    public ISysUser setSqlStatus(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_SQL_STATUS, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getSqlStatus() {
			return getInt(COL_SQL_STATUS);
	}
    public ISysUser setState(java.lang.Integer value) {
    		if(value != 0){
    			set(COL_STATE, value);
    		}
        return this;
    }
    
 

	public java.lang.Integer getState() {
			return getInt(COL_STATE);
	}
    public ISysUser setChannelId(java.lang.String value) {
			if (!ToolString.isNull(value)) {
    			set(COL_CHANNEL_ID, value);
    		}
        return this;
    }
    
 

	public java.lang.String getChannelId() {
			return getStr(COL_CHANNEL_ID);
	}
	
	
	  
	  	    public static final String ALL_COLS =" id,uuid,nickname,mobile,account,email,password,salt,company_uuid,account_type,user_pt,head_url,super_uuid,sex,is_auth,card_number,role_uuid,version,create_uuid,update_uuid,disable_uuid,del_uuid,create_at,update_at,disable_at,del_at,sql_status,state,channel_id";
	    public static final String DEFAULT_SQL ="select  id,uuid,nickname,mobile,account,email,password,salt,company_uuid,account_type,user_pt,head_url,super_uuid,sex,is_auth,card_number,role_uuid,version,create_uuid,update_uuid,disable_uuid,del_uuid,create_at,update_at,disable_at,del_at,sql_status,state,channel_id  from sys_user where 1=1";
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[uuid:" + getUuid() + "]";
		log += "[nickname:" + getNickname() + "]";
		log += "[mobile:" + getMobile() + "]";
		log += "[account:" + getAccount() + "]";
		log += "[email:" + getEmail() + "]";
		log += "[password:" + getPassword() + "]";
		log += "[salt:" + getSalt() + "]";
		log += "[companyUuid:" + getCompanyUuid() + "]";
		log += "[accountType:" + getAccountType() + "]";
		log += "[userPt:" + getUserPt() + "]";
		log += "[headUrl:" + getHeadUrl() + "]";
		log += "[superUuid:" + getSuperUuid() + "]";
		log += "[sex:" + getSex() + "]";
		log += "[isAuth:" + getIsAuth() + "]";
		log += "[cardNumber:" + getCardNumber() + "]";
		log += "[roleUuid:" + getRoleUuid() + "]";
		log += "[version:" + getVersion() + "]";
		log += "[createUuid:" + getCreateUuid() + "]";
		log += "[updateUuid:" + getUpdateUuid() + "]";
		log += "[disableUuid:" + getDisableUuid() + "]";
		log += "[delUuid:" + getDelUuid() + "]";
		log += "[createAt:" + getCreateAt() + "]";
		log += "[updateAt:" + getUpdateAt() + "]";
		log += "[disableAt:" + getDisableAt() + "]";
		log += "[delAt:" + getDelAt() + "]";
		log += "[sqlStatus:" + getSqlStatus() + "]";
		log += "[state:" + getState() + "]";
		log += "[channelId:" + getChannelId() + "]";
		return log;
	}
}