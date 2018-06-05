package cn.wyb.model.param;

public class AMapPlaceDetailParam extends AMapApiBaseParam {

	private String uid;//	是,poi的uid
	private String uids;//是,uid的集合，最多可以传入10个uid，多个uid之间用英文逗号分隔。
	private String scope;//检索结果详细程度。

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
