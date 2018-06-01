package cn.wyb.model.vo;

import java.util.List;

public class AMapResponse<T> {

	private Integer status;        //本次API访问状态，如果成功返回0，如果失败返回其他数字。（见服务状态码）
	private String message;        //对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
	private Integer total;        //POI检索总数，开发者请求中设置了page_num字段才会出现total字段。出于数据保护目的，单次请求total最多为400。
	private List<T> results;

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

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
