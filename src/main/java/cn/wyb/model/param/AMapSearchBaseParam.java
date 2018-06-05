package cn.wyb.model.param;

public abstract class AMapSearchBaseParam extends AMapApiBaseParam {

	private String scope;//检索结果详细程度。
	private String query;//检索关键字，周边检索和矩形区域内检索支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”。检索词支持中英文。(45)东京塔,必选
	private String tag;//	检索分类，与query组合进行检索，多个分类以","分隔 POI分类）(50),美食,可选
	private String ret_coordtype;//可选参数，添加后POI返回国测局经纬度坐标（港澳台有效） 坐标详细说明,50)gcj02ll	可选
	private Integer page_size;//单次召回POI数量，默认为10条记录，最大返回20条。多关键字检索时，返回的记录数为关键字个数*page_size。可选
	private Integer page_num;//分页页码，默认为0,0代表第一页，1代表第二页，以此类推。 常与page_size搭配使用。0、1、2,可选

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRet_coordtype() {
		return ret_coordtype;
	}

	public void setRet_coordtype(String ret_coordtype) {
		this.ret_coordtype = ret_coordtype;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
