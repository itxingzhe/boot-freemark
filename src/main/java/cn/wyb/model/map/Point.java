package cn.wyb.model.map;

import java.util.Objects;

public class Point {

	public Point(){}

	public Point(Double lng,Double lat){
		this.lng = lng;
		this.rat = rat;
	}

	private Double lng;
	private Double rat;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return Objects.equals(lng, point.lng) &&
				Objects.equals(rat, point.rat);
	}

	@Override
	public int hashCode() {

		return Objects.hash(lng, rat);
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getRat() {
		return rat;
	}

	public void setRat(Double rat) {
		this.rat = rat;
	}
}
