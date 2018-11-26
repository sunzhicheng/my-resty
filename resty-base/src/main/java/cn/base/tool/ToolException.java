package cn.base.tool;

import cn.dreampie.common.http.HttpMessage;
import cn.dreampie.common.http.exception.HttpException;
import cn.dreampie.common.http.result.HttpStatus;

public class ToolException {

	public static HttpException getEx(String msg) {
		return  new HttpException(HttpStatus.OK,HttpMessage.INTERNAL_SERVER_ERROR,msg);
	}
}
