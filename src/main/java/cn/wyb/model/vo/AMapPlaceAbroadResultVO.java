package cn.wyb.model.vo;

import cn.wyb.model.map.AMapPlaceDetailInfo;
import cn.wyb.model.map.Point;

public class AMapPlaceAbroadResultVO {

	private String name;        //poi名称
	private Point location;        //poi经纬度坐标
	private String address;        //poi地址信息
	private String telephone;        //poi电话信息
	private String uid;        //poi的唯一标示
	private String street_id;        //街景图id
	private String detail;        //是否有详情页：1有，0没有
	private AMapPlaceDetailInfo detail_info;        //poi的扩展信息，仅当scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public AMapPlaceDetailInfo getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(AMapPlaceDetailInfo detail_info) {
		this.detail_info = detail_info;
	}
}
