package cn.wyb.personal.model.vo.map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "aMapAddressDetailVO", description = "地址详细信息")
public class BmapAddressDetailVO {
	/**
	 * 城市
	 */
	@ApiModelProperty(value = "城市")
	private String city;
	/**
	 * 百度城市代码
	 */
	@ApiModelProperty(value = "百度城市代码")
	private Integer city_code;
	/**
	 * 区县
	 */
	@ApiModelProperty(value = "区县")
	private String district;
	/**
	 * 省份
	 */
	@ApiModelProperty(value = "省份")
	private String province;
	/**
	 * 街道
	 */
	@ApiModelProperty(value = "街道")
	private String street;
	/**
	 * 门牌号
	 */
	@ApiModelProperty(value = "门牌号")
	private String street_number;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCity_code() {
		return city_code;
	}

	public void setCity_code(Integer city_code) {
		this.city_code = city_code;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
}
