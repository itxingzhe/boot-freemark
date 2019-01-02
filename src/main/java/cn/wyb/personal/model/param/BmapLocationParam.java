package cn.wyb.personal.model.param;

import javax.validation.constraints.NotBlank;

public class BmapLocationParam extends BmapApiBaseParam {

    public BmapLocationParam() {
    }

    public BmapLocationParam(String ip) {
        this.ip = ip;
    }

	/**
	 * 用户上网的IP地址
	 */
    @NotBlank
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
