package cn.wyb.personal.common.result;

public class CommResponse {

	private String code;
	private String message;
	private Object data;

	public static CommResponse success(Object data) {
		CommResponse cr = new CommResponse();
		cr.setMessage("恭喜您，操作成功！");
		cr.setData(data);
		cr.setCode("200");
		return cr;
	}

	public static CommResponse failure(Object data) {
		CommResponse cr = new CommResponse();
		cr.setMessage("抱歉，操作失败！");
		cr.setData(data);
		cr.setCode("500");
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
