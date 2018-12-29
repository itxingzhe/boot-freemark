package cn.wyb.personal.model.vo.bmap;

public class PointStrVO {

	public PointStrVO() {
	}

	public PointStrVO(String x, String y) {
		this.x = x;
		this.y = y;
	}

    private String x;// 经度值lng
    private String y;// 纬度值lat

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
}
