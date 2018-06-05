package cn.wyb.model.param;

public class AMapSearchSuggestionParam extends AMapApiBaseParam {
	private String region;//检索行政区划区域，当前仅支持文本检索,(50)东京,必选
	private String query;//检索关键字，周边检索和矩形区域内检索支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”。检索词支持中英文。(45)东京塔,必选
	private String ret_coordtype;//可选参数，添加后POI返回国测局经纬度坐标（港澳台有效） 坐标详细说明,50)gcj02ll	可选

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRet_coordtype() {
		return ret_coordtype;
	}

	public void setRet_coordtype(String ret_coordtype) {
		this.ret_coordtype = ret_coordtype;
	}
}
