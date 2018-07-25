package cn.wyb.personal.model.vo;

import cn.wyb.personal.model.vo.map.Point;

public class GeolocationResult {

	private Integer accuracy;
	private Point point;

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
