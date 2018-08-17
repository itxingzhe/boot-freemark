package cn.wyb.personal.model.vo.bmap;

import com.alibaba.fastjson.JSONArray;

public class BmapPlaceDetailInfoVO {
	private Long distance;//	距离中心点的距离，圆形区域检索时返回
	private String type;//	所属分类，如’hotel’、’cater’。
	private String tag;//	标签
	private String detail_url;//	poi的详情页
	private String price;//	poi商户的价格
	private String shop_hours;//	营业时间
	private String overall_rating;//	总体评分
	private String taste_rating;//	口味评分
	private String service_rating;//	服务评分
	private String environment_rating;//	环境评分
	private String facility_rating;//	星级（设备）评分
	private String hygiene_rating;//	卫生评分
	private String technology_rating;//	技术评分
	private String image_num;//	图片数
	private Integer groupon_num;//团购数
	private Integer discount_num;//优惠数
	private String comment_num;//	评论数
	private String favorite_num;//	收藏数
	private String checkin_num;//	签到数
	private String image;//图片地址
	private JSONArray di_review_keyword;

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShop_hours() {
		return shop_hours;
	}

	public void setShop_hours(String shop_hours) {
		this.shop_hours = shop_hours;
	}

	public String getOverall_rating() {
		return overall_rating;
	}

	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}

	public String getTaste_rating() {
		return taste_rating;
	}

	public void setTaste_rating(String taste_rating) {
		this.taste_rating = taste_rating;
	}

	public String getService_rating() {
		return service_rating;
	}

	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}

	public String getEnvironment_rating() {
		return environment_rating;
	}

	public void setEnvironment_rating(String environment_rating) {
		this.environment_rating = environment_rating;
	}

	public String getFacility_rating() {
		return facility_rating;
	}

	public void setFacility_rating(String facility_rating) {
		this.facility_rating = facility_rating;
	}

	public String getHygiene_rating() {
		return hygiene_rating;
	}

	public void setHygiene_rating(String hygiene_rating) {
		this.hygiene_rating = hygiene_rating;
	}

	public String getTechnology_rating() {
		return technology_rating;
	}

	public void setTechnology_rating(String technology_rating) {
		this.technology_rating = technology_rating;
	}

	public String getImage_num() {
		return image_num;
	}

	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}

	public Integer getGroupon_num() {
		return groupon_num;
	}

	public void setGroupon_num(Integer groupon_num) {
		this.groupon_num = groupon_num;
	}

	public Integer getDiscount_num() {
		return discount_num;
	}

	public void setDiscount_num(Integer discount_num) {
		this.discount_num = discount_num;
	}

	public String getComment_num() {
		return comment_num;
	}

	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}

	public String getFavorite_num() {
		return favorite_num;
	}

	public void setFavorite_num(String favorite_num) {
		this.favorite_num = favorite_num;
	}

	public String getCheckin_num() {
		return checkin_num;
	}

	public void setCheckin_num(String checkin_num) {
		this.checkin_num = checkin_num;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public JSONArray getDi_review_keyword() {
		return di_review_keyword;
	}

	public void setDi_review_keyword(JSONArray di_review_keyword) {
		this.di_review_keyword = di_review_keyword;
	}
}
