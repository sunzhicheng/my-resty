package cn.base.tool;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.dreampie.common.util.Stringer;

/**
 * 字符串工具类
 * 
 * @author haoyin
 *
 */
public final class ToolString extends  Stringer {
	
	private ToolString() {
		// 工具类，不需要实例化
	}

	public static final String CHAR_SET_UTF_8 = "UTF-8";
	
	public static final String SPLIT_DH = ",";

	/**
	 * 随机字符串生成基础
	 */
	private static final String NONCE_BASE_STR = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * 网络链接前缀
	 */
	public final static String HTTP_PREFIX = "http://";
	public final static String HTTPS_PREFIX = "https://";
	
	/**
	 * 正则与连接符
	 */
	public final static String REGX_OR = "|";
	

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(Object  s) {
		String str = String.valueOf(s);
		return str == null || str.trim().length() == 0 || "null".equals(str)||"NULL".equals(str);
	}
	public static boolean isNotNull(Object  s) {
		return !isNull(s);
	}
	
	public static boolean isNull(Object...  s) {
		for (Object obj : s) {
			if(isNotNull(obj)) {
				return false;
			}
		}
		return true;
	}
	public static boolean isNotNull(Object...  s) {
		for (Object obj : s) {
			if(isNull(obj)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否为空，包括空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullIgnoreSpace(String str) {
		return str == null || str.length() == 0 || str.trim().length() == 0;
	}

	/**
	 * 是否为HTTP 链接
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isHttpUrl(String url) {
		return !isNull(url) && (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX));
	}

	/**
	 * 判断是否为正整型数
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
		return isNumeric(str, "[0-9]*");
	}

	/**
	 * 判断是否为整型数
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumericA(String str) {
		return isNumeric(str, "-?[0-9]*");
	}

	/**
	 * 判断是否为正浮点数
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumericDouble(String str) {
		return isNumeric(str, "[0-9]+.?[0-9]+");
	}

	/**
	 * 判断是否为符合规则数字
	 * 
	 * @param str
	 * @param regx
	 * @return
	 */
	public static boolean isNumeric(String str, String regx) {
		if (str != null && !"".endsWith(str)) {
			Pattern pattern = Pattern.compile(regx);
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 整数包含负数
	 * @param str
	 * @return
	 */
	public static boolean isNumericF(String str)
	{
		return isNumeric(str, "^(-)?[1-9][0-9]*$");
	} 


	/**
	 * 创建字符串
	 * @param length 需要的长度
	 * @return
	 */
	public static String getNonceStr(int length) {
		int baseCount = NONCE_BASE_STR.length();
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseCount);
			sb.append(NONCE_BASE_STR.charAt(number));
		}
		String noncestr = sb.toString();
		return noncestr;

	}

	public static double parseDouble(String str)
	{
//		if (isNumeric(str)) {
//			return Double.valueOf(str);
//		}
//		return 0;
		
		if(ToolString.isNull(str))return 0.0;
		//return StringUtil.fmt2double(str);
		double result = 0;
		
		if(null==str)
			return result;
		boolean isFraction = false;
		boolean minus = false;
		int times = 10;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)>='0' && str.charAt(i)<='9')
			{
				if(isFraction)
				{
					if(!minus)
						result = result + (str.charAt(i)-48.0)/times;
					else
						result = result - (str.charAt(i)-48.0)/times;
					times *= 10;
				}
				else
					if(!minus)
						result = result*10 + str.charAt(i)-48;
					else
						result = result*10 - str.charAt(i)+48;
				
			}
			else if(str.charAt(i)=='.')
			{
				if(isFraction) break;
				isFraction = true;
			}
			else if(str.charAt(i)==' ')
			{
				continue;
			}
			else if(str.charAt(i)=='-')
			{
				if(Math.abs(result-0.0)<0.001)
				{
					minus = true;
				}
				else
				{
					break;
				}
			}
			else if(str.charAt(i)=='+')
			{
				if(Math.abs(result-0.0)>0.001) break;
			}
			else if(result>0)
			{
				break;
			}
		}
		return result;
	}
	public static int parseInt(String str)
	{
		
//		if (isNumeric(str)) {
//			return Integer.valueOf(str);
//		}
//		return 0;
		
		if(ToolString.isNull(str))return 0;
		int result = 0;
		
		if(null==str)
			return result;
		
		boolean minus =false;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)>='0' && str.charAt(i)<='9')
			{
				if(!minus)
					result = result*10 + str.charAt(i)-48;
				else
					result = result*10 - str.charAt(i) + 48;
			}	
			else if(str.charAt(i)==' ')
			{
				continue;
			}
			else if(str.charAt(i)=='-')
			{
				if(result==0)
					minus = true;
				else
					break;
			}
			else if(str.charAt(i)=='+')
			{
				if(result!=0) break;
			}
			else if(result!=0)
			{
				break;
			}
		}
		return result;
	}   
 
	public static long parseLong(String str)
	{
		if(ToolString.isNull(str))return 0;
		long result = 0;
		
		if(null==str)
			return result;
		
		boolean minus =false;
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)>='0' && str.charAt(i)<='9')
			{
				if(!minus)
					result = result*10 + str.charAt(i)-48;
				else
					result = result*10 - str.charAt(i) + 48;
			}	
			else if(str.charAt(i)==' ')
			{
				continue;
			}
			else if(str.charAt(i)=='-')
			{
				if(result==0)
					minus = true;
				else
					break;
			}
			else if(str.charAt(i)=='+')
			{
				if(result!=0) break;
			}
			else if(result!=0)
			{
				break;
			}
		}
		return result;
	}   
	
	public static boolean parseBoolean(String str){
		return "true".equalsIgnoreCase(str.trim());
	}
	
	public static void main(String[] args) {
		System.out.println(parseInt("1.1111"));
	}


	/**
	 * 替换URL转义字符
	 * @param urlStr
	 * @return String
	 */
	public static String replaceUrlSplit(String urlStr) {
        String dest = "";
        if (urlStr!=null) {
            Pattern p = Pattern.compile("\\\\");
            Matcher m = p.matcher(urlStr);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
	 * 判断是否帐号 （字母加数字）
	 * @param s
	 * @return
	 */
	public static boolean  isAccount(String s) {
		String reg="^[a-zA-Z0-9]{2,16}$";
		return s.matches(reg);
	}

	public static String subStringCN(final String str, final int maxLength) {
		if (str == null) {
			return str;
		}
		String suffix = "...";
		int suffixLen = suffix.length();
		
		final StringBuffer sbuffer = new StringBuffer();
		final char[] chr = str.trim().toCharArray();
		int len = 0;
		for (int i = 0; i < chr.length; i++) {
			
			if (chr[i] >= 0xa1) {
				len += 2;
			} else {
				len++;
			}
		}
		
		if(len<=maxLength){
			return str;
		}
		
		len = 0;
		for (int i = 0; i < chr.length; i++) {
 
			if (chr[i] >= 0xa1) {
				len += 2;
				if (len + suffixLen > maxLength) {
					break;
				}else {
					sbuffer.append(chr[i]);
				}
			} else {
				len++;
				if (len + suffixLen > maxLength) {
					break;
				}else {
					sbuffer.append(chr[i]);
				}
			}
		}
		sbuffer.append(suffix);
		return sbuffer.toString();
	}
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
