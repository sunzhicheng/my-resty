package cn.base.tool;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 SHA 加密工具类
 * @author haoyin
 *
 */
public final class ToolMd5 {
	
	private ToolMd5() {
		// 工具类，不需要实例化
	}
	/**
	 * pwd 密码
	 * nonce 随机字符串
	 */
	private static final String S_FORAMT_PWD = "pwd_%s_nonce_%s"; 
	/**
	 * 生成强密码, 统一小写
	 * @param loginPwd 明文密码
	 * @param pwdNonceStr 加密密钥
	 * @return String
	 */
	public static String generateStrongPwd(String loginPwd, String pwdNonceStr) {
		return sha1Sign(String.format(S_FORAMT_PWD, loginPwd,pwdNonceStr)).toLowerCase();
	}
    /**
     * MD5加密签名
     * @param strObj
     * @return String
     */
    public static String md5Sign(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
    	return DigestUtils.md5Hex(strObj);
    }
    
    /**
     * MD5加密签名 16位 8-24
     * @param strObj
     * @return String
     */
    public static String md5Sign8To24(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
        return md5Sign(strObj).substring(8, 24);
    }
    
    /**
     * MD5加密签名 8位 8-16
     * @param strObj
     * @return String
     */
    public static String md5Sign8To16(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
        return md5Sign(strObj).substring(8, 16);
    }
    
    /**
     * SHA1加密签名
     * @param strObj
     * @return String
     */
    public static String sha1Sign(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
    	return DigestUtils.sha1Hex(strObj);
    }
    
    /**
     * SHA1加密签名 16位 8-24
     * @param strObj
     * @return String
     */
    public static String sha1Sign8To24(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
        return sha1Sign(strObj).substring(8, 24);
    }
    
    /**
     * SHA1加密签名 8位 8-16
     * @param strObj
     * @return String
     */
    public static String sha1Sign8To16(String strObj) {
    	if (ToolString.isNull(strObj)){
    		return "";
    	}
        return sha1Sign(strObj).substring(8, 16);
    }
    
    public static void main(String[] args) {
		System.out.println(generateStrongPwd("123456", "sunzc"));
	}
    
}
