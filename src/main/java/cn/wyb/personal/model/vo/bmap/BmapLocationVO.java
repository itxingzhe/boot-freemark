package cn.wyb.personal.model.vo.bmap;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "aMapLocationVO", description = "地址信息")
public class BmapLocationVO {

	/**
	 * 简要地址信息
	 */
	@ApiModelProperty(value = "简要地址信息")
	private String address;
	/**
	 * 当前城市中心点
	 */
	@ApiModelProperty(value = "当前城市中心点")
	private PointStrVO point;
	/**
	 * 结构化地址信息
	 */
	@ApiModelProperty(value = "结构化地址信息")
	private BmapAddressDetailVO address_detail;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PointStrVO getPoint() {
		return point;
	}

	public void setPoint(PointStrVO point) {
		this.point = point;
	}

	public BmapAddressDetailVO getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(BmapAddressDetailVO address_detail) {
		this.address_detail = address_detail;
	}
}
