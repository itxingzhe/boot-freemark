package cn.wyb.model.param;

//周边检索
public class AMapSearchCircumParam extends AMapSearchBaseParam {
	private String location;//周边检索中心点，不支持多个点	string(50)	35.711343,139.767111 lat<纬度>,lng<经度> 必选
	private String radius;//周边检索半径，单位为米。	string(50)	1000（默认）	可选
	private Integer coord_type;//坐标类型，1（wgs84ll即GPS经纬度），2（gcj02ll即国测局经纬度坐标，港澳台可用），3（bd09ll即百度经纬度坐标） 坐标详细说明	int	1、2、3(默认)、	可选

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public Integer getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(Integer coord_type) {
		this.coord_type = coord_type;
	}
}
