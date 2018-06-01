package cn.wyb.model.map;

import cn.wyb.model.vo.AMapPlaceAbroadResultVO;

import java.util.List;

public class AMapPlaceAbroadResponse {

	private int status;        //本次API访问状态，如果成功返回0，如果失败返回其他数字。（见服务状态码）
	private String message;        //对API访问状态值的英文说明，如果成功返回"ok"，并返回结果字段，如果失败返回错误说明。
	private int total;        //POI检索总数，开发者请求中设置了page_num字段才会出现total字段。出于数据保护目的，单次请求total最多为400。
	private List<AMapPlaceAbroadResultVO> results;

}
