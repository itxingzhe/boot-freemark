package cn.wyb.model.param;

public class BmapCoordsParam extends BmapApiBaseParam {

	/**
	 * 需转换的源坐标，多组坐标以“；”分隔 （经度，纬度）
	 */
	private String coords;

	/**
	 * 源坐标类型：
	 * 1：GPS设备获取的角度坐标，wgs84坐标;
	 * 2：GPS获取的米制坐标、sogou地图所用坐标;
	 * 3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局（gcj02）坐标;
	 * 4：3中列表地图坐标对应的米制坐标;
	 * 5：百度地图采用的经纬度坐标;
	 * 6：百度地图采用的米制坐标;
	 * 7：mapbar地图坐标;
	 * 8：51地图坐标
	 */
	private Integer from;

	/**
	 * 目标坐标类型：
	 * 3：国测局（gcj02）坐标;
	 * 4：3中对应的米制坐标;
	 * 5：bd09ll(百度经纬度坐标);
	 * 6：bd09mc(百度米制经纬度坐标)
	 */
	private Integer to;

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}
}
