package cn.wyb.personal.model.param;

public class AmapCoordinateParam {

	private String locations;
	/*可选值：gps;mapbar;baidu;utonavi(不进行转换)*/
	private String coordsys = "baidu";
	private String output;

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getCoordsys() {
		return coordsys;
	}

	public void setCoordsys(String coordsys) {
		this.coordsys = coordsys;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
