package cn.wyb.personal.common.result;

import cn.wyb.personal.common.exception.BaseException;

/**
 * CommResponse: 公共响应类.
 *
 * @author wangyibin
 * @date 2018/7/26 10:25
 * @see
 */
public class CommResponse {

	private String code;
	private String message;
	private Object data;

	/**
	 * success : 正常请求响应.
	 *
	 * @return cn.wyb.personal.common.result.CommResponse
	 * @author wangyibin
	 * @date 2018/7/26 10:33
	 */
	public static CommResponse success() {
		return success(null);
	}

	/**
	 * success : 正常请求响应.
	 *
	 * @author wangyibin
	 * @date 2018/7/26 10:33
	 * @param data
	 * @return cn.wyb.personal.common.result.CommResponse
	 *
	 */
	public static CommResponse success(Object data) {
		CommResponse cr = new CommResponse();
		cr.setMessage("恭喜您，操作成功！");
		cr.setData(data);
		cr.setCode("200");
		return cr;
	}

	/**
	 * failure : 异常请求响应.
	 *
	 * @author wangyibin
	 * @date 2018/7/26 10:34
	 * @param data
	 * @return cn.wyb.personal.common.result.CommResponse
	 *
	 */
	public static CommResponse failure(Object data) {
		return failure("500", "抱歉，操作失败！", data);
	}

	/**
	 * failure : 异常请求响应.
	 *
	 * @param message
	 * @return cn.wyb.personal.common.result.CommResponse
	 * @author wangyibin
	 * @date 2018/7/26 10:39
	 */
	public static CommResponse failure(String message) {
		return failure("500", message);
	}

	/**
	 * failure : 异常请求响应.
	 *
	 * @param code
	 * @param message
	 * @return cn.wyb.personal.common.result.CommResponse
	 * @author wangyibin
	 * @date 2018/7/26 10:37
	 */
	public static CommResponse failure(String code, String message) {
		return failure(code, message, null);
	}

	/**
	 * failure : 异常请求响应.
	 *
	 * @param code
	 * @param message
	 * @param data
	 * @return cn.wyb.personal.common.result.CommResponse
	 * @author wangyibin
	 * @date 2018/7/26 10:34
	 */
	public static CommResponse failure(String code, String message, Object data) {
		CommResponse cr = new CommResponse();
		cr.setMessage(message);
		cr.setData(data);
		cr.setCode(code);
		return cr;
	}

	/**
	 * failure : 异常请求响应.
	 *
	 * @param e
	 * @return cn.wyb.personal.common.result.CommResponse
	 * @author wangyibin
	 * @date 2018/7/30 10:13
	 */
	public static CommResponse failure(BaseException e) {
		CommResponse cr = new CommResponse();
		if (e != null) {
			cr.setMessage(e.getErrorMessage());
			cr.setCode(e.getErrorCode());
		}
		return cr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
