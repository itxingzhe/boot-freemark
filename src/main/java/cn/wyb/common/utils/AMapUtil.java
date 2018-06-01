package cn.wyb.common.utils;

import cn.wyb.common.enums.AMapApiStatusCodeEnum;
import cn.wyb.model.param.*;
import cn.wyb.model.vo.AMapPlaceAbroadResultVO;
import cn.wyb.model.vo.AMapResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class AMapUtil {

	private static final String AMAP_AK = "ev82Imq86gQmRRZT5Chobk35KKPPh3NB";
	private static final String AMAP_ROOT_URL = "http://api.map.baidu.com";
	private static final String AMAP_PLACE_ABROAD_PORT = "/place_abroad/v1";
	private static final String POINT_SEARCH = "/search";
	private static final String POINT_DETAIL = "/detail";

	public static Map<String, Object> getParamMap(AMapApiBaseParam param) {
		Map<String, Object> map = (Map<String, Object>) BeanConvertor.objectToMap(param);
		String output = (String) map.get("output");
		if (StringUtils.isBlank(output)) {
			output = "json";
		}
		output = "json".equals(output.toLowerCase()) ? "json" : "xml";
		map.put("output", output);
		map.put("ak", AMAP_AK);
		return map;
	}

	public static JSONObject getAMapAPIResponse(String url, AMapApiBaseParam param) {
		if (param == null) {
			return null;
		}
		Map<String, Object> map = getParamMap(param);
		String s = HttpUtil.sendGet(url, map);
		System.out.println(s);
		if (StringUtils.isAllBlank(s)) {
			return null;
		}
		return JSON.parseObject(s);
	}

	public static AMapResponse<AMapPlaceAbroadResultVO> getSearchResponse(String port, AMapApiBaseParam param) {
		String url = AMAP_ROOT_URL + AMAP_PLACE_ABROAD_PORT + port;
		JSONObject jsonObject = getAMapAPIResponse(url, param);
		AMapResponse<AMapPlaceAbroadResultVO> response = jsonObject.toJavaObject(AMapResponse.class);
		if (AMapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
			JSONArray results = jsonObject.getJSONArray("results");
			if (results != null) {
				response.setResults(results.toJavaList(AMapPlaceAbroadResultVO.class));
			}
		}
		return response;
	}

	//地点检索查询
	public static AMapResponse<AMapPlaceAbroadResultVO> getSearchPlaceResponse(AMapSearchBaseParam param) {
		AMapResponse<AMapPlaceAbroadResultVO> response = getSearchResponse(POINT_SEARCH, param);
		return response;
	}

	//地点详情检索
	public static AMapResponse<AMapPlaceAbroadResultVO> searchPlaceDetail(AMapPlaceDetailParam param) {
		AMapResponse<AMapPlaceAbroadResultVO> response = getSearchResponse(POINT_DETAIL, param);
		return response;
	}


	//行政区划区域检索
	public static AMapResponse<AMapPlaceAbroadResultVO> searchRegionalism(AMapSearchRegionalismParam param) {
		return getSearchPlaceResponse(param);
	}

	//周边检索
	public static AMapResponse<AMapPlaceAbroadResultVO> searchCircum(AMapSearchCircumParam param) {
		return getSearchPlaceResponse(param);
	}

	//矩形区域检索
	public static AMapResponse<AMapPlaceAbroadResultVO> searchRectangle(AMapSearchRectangleParam param) {
		return getSearchPlaceResponse(param);
	}


	public static void main(String[] args) {
		AMapSearchRegionalismParam param = new AMapSearchRegionalismParam();
		param.setQuery("华尔街");
		param.setRegion("纽约");
		param.setScope("2");
		AMapResponse<AMapPlaceAbroadResultVO> searchResponse = searchRegionalism(param);
		String message = searchResponse.getMessage();
		Integer status = searchResponse.getStatus();
		List<AMapPlaceAbroadResultVO> results = searchResponse.getResults();
		String uid = results.get(3).getUid();
		AMapPlaceDetailParam detailParam = new AMapPlaceDetailParam();
		detailParam.setUid(uid);
		detailParam.setUids(uid);
		detailParam.setScope("1");
		AMapResponse<AMapPlaceAbroadResultVO> detailResponse = searchPlaceDetail(detailParam);
		detailResponse.getStatus();
		detailResponse.getMessage();
	}

}
