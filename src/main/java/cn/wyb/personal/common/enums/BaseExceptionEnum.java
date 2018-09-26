package cn.wyb.personal.common.enums;

/**
 * BaseExceptionEnum: 异常枚举类.
 *
 * @author wangyibin
 * @date 2018/7/30 9:31
 * @see
 */
public enum BaseExceptionEnum {

	MODEL_AND_VIEW_ERROR("100000001", "页面访问异常！"),

	NOT_QUERY__TO_THE_DATA("200000001", "未查询到数据！"),


	COMMON_500("500", "%s");
	/**
	 * 异常编码
	 */
	private String code;

	/**
	 * 异常消息
	 */
	private String msg;

	BaseExceptionEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
