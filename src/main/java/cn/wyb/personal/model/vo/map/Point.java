package cn.wyb.personal.model.vo.map;

import java.util.Objects;

public class Point {

	public Point(){}

	public Point(Float lng, Float lat) {
		this.lng = lng;
		this.lat = this.lat;
	}

	private Float lng;//经度值
	private Float lat;//纬度值

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return Objects.equals(lng, point.lng) &&
				Objects.equals(lat, point.lat);
	}

	@Override
	public int hashCode() {

		return Objects.hash(lng, lat);
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return lng + "," + lat;
	}
}
