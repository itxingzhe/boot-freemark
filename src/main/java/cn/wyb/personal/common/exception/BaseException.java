package cn.wyb.personal.common.exception;

import cn.wyb.personal.common.enums.BaseExceptionEnum;

/**
 * BaseException: 异常基础类.
 *
 * @author wangyibin
 * @date 2018/7/27 16:18
 * @see
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -2578345591910789687L;

	/**
	 * 异常编码
	 */
	private String errorCode;

	/**
	 * 异常信息
	 */
	private String errorMessage;

	/**
	 * 异常信息参数
	 */
	private Object[] arge;

	public BaseException() {
		super();
	}

	public BaseException(String errorCode, String errorMessage, Object... arge) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.arge = arge;
	}

	public BaseException(Throwable cause, String errorCode, String errorMessage, Object... arge) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.arge = arge;
	}

	public BaseException(BaseExceptionEnum baseExceptionEnum, Object... arge) {
		this.errorCode = baseExceptionEnum.getCode();
		this.errorMessage = baseExceptionEnum.getMsg();
		this.arge = arge;
	}

	public BaseException(BaseExceptionEnum baseExceptionEnum, Throwable cause, Object... arge) {
		super(cause);
		this.errorCode = baseExceptionEnum.getCode();
		this.errorMessage = baseExceptionEnum.getMsg();
		this.arge = arge;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object[] getArge() {
		return arge;
	}

	public void setArge(Object[] arge) {
		this.arge = arge;
	}
}
