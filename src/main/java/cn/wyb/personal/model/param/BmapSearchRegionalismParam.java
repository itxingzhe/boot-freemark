package cn.wyb.personal.model.param;

//检索行政区划区域
public class BmapSearchRegionalismParam extends BmapSearchBaseParam {

	private String region;//检索行政区划区域，当前仅支持文本检索,(50)东京,必选

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
