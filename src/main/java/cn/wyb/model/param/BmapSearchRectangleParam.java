package cn.wyb.model.param;

//矩形区域检索
public class BmapSearchRectangleParam extends BmapSearchBaseParam {
	private String bounds;//检索矩形区域，多组坐标间以","分隔	string(50)	38.76623,116.43213,39.54321,116.46773 lat,lng(左下角坐标),lat,lng(右上角坐标)	必选
	private Integer coord_type;//坐标类型，1（wgs84ll即GPS经纬度），2（gcj02ll即国测局经纬度坐标，港澳台可用），3（bd09ll即百度经纬度坐标） 坐标详细说明	int	1、2、3(默认)、	可选

	public String getBounds() {
		return bounds;
	}

	public void setBounds(String bounds) {
		this.bounds = bounds;
	}

	public Integer getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(Integer coord_type) {
		this.coord_type = coord_type;
	}
}
