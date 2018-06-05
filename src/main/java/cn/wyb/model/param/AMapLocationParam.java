package cn.wyb.model.param;

public class AMapLocationParam extends AMapApiBaseParam {

	/**
	 * 用户上网的IP地址
	 */
	private String ip;
	/**
	 * 设置返回位置信息中，经纬度的坐标类型
	 */
	private String coor;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCoor() {
		return coor;
	}

	public void setCoor(String coor) {
		this.coor = coor;
	}
}
