package cn.wyb.personal.model.vo.bmap;

public class BmapPlaceAbroadResultVO {

	private String name;        //poi名称
	private Point location;        //poi经纬度坐标
	private String address;        //poi地址信息
	private String telephone;        //poi电话信息
	private String uid;        //poi的唯一标示
	private String street_id;        //街景图id
	private String detail;        //是否有详情页：1有，0没有
	private BmapPlaceDetailInfoVO detail_info;        //poi的扩展信息，仅当scope=2时，显示该字段，不同的poi类型，显示的detail_info字段不同。
	private String city;//城市
	private String district;//区县
	private String tag;//poi分类

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

	public BmapPlaceDetailInfoVO getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(BmapPlaceDetailInfoVO detail_info) {
		this.detail_info = detail_info;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
