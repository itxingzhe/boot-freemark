package cn.wyb.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "aMapResponse", description = "api响应类")
public class AMapResponse<T> {

	@ApiModelProperty("本次API访问状态，如果成功返回0，如果失败返回其他数字。")
	private Integer status;

	@ApiModelProperty("对API访问状态值的英文说明")
	private String message;

	@ApiModelProperty("POI检索总数，开发者请求中设置了page_num字段才会出现total字段。")
	private Integer total;

	private List<T> result;

	@ApiModelProperty("响应数据")
	private Object data;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
