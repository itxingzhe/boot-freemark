package cn.wyb.common.result;

import cn.wyb.model.vo.map.AMapLocationVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "aMapLocationResponse", description = "IP定位响应类")
public class AMapLocationResponse {

	@ApiModelProperty("本次API访问状态，如果成功返回0，如果失败返回其他数字。")
	private Integer status;

	@ApiModelProperty("详细地址信息")
	private String address;

	@ApiModelProperty("定位到的地址信息")
	private AMapLocationVO content;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AMapLocationVO getContent() {
		return content;
	}

	public void setContent(AMapLocationVO content) {
		this.content = content;
	}
}
