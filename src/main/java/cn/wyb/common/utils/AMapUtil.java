package cn.wyb.common.utils;

import cn.wyb.common.enums.AMapApiStatusCodeEnum;
import cn.wyb.model.param.*;
import cn.wyb.model.vo.AMapPlaceAbroadResultVO;
import cn.wyb.model.vo.AMapResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class AMapUtil {

	private static final String AMAP_AK = "ev82Imq86gQmRRZT5Chobk35KKPPh3NB";
	private static final String AMAP_ROOT_URL = "http://api.map.baidu.com";
	private static final String AMAP_PLACE_ABROAD_PORT = "/place_abroad/v1";
	private static final String POINT_SEARCH = "/search";
	private static final String POINT_DETAIL = "/detail";

	public static Map<String, String> getParamMap(AMapApiBaseParam param) {

		HashMap<String, String> map = Maps.newHashMap();
		Class clazz = param.getClass();
		List<Field> fieldList = new ArrayList<>();
		while (clazz != Object.class) {
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		for (Field field : fieldList) {
			field.setAccessible(true);
			String name = field.getName();
			if (StringUtils.isNotBlank(name)) {
				try {
					Object o = field.get(param);
					if (o != null && StringUtils.isNotBlank(o.toString())) {
						map.put(name, o.toString());
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		String output = param.getOutput();
		if (StringUtils.isBlank(output)) {
			output = "json";
		}
		output = "xml".equals(output.toLowerCase()) ? "xml" : "json";
		map.put("output", output);
		map.put("ak", AMAP_AK);
		return map;
	}

	public static JSONObject getAMapAPIResponse(String url, AMapApiBaseParam param) {
		if (param == null) {
			return null;
		}
		Map<String, String> map = getParamMap(param);
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
		AMapResponse response = jsonObject.toJavaObject(AMapResponse.class);
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


		//AMapSearchCircumParam param = new AMapSearchCircumParam();
		//param.setQuery("寿司");
		//param.setLocation("35.711343,139.767111");

		//AMapSearchRectangleParam param = new AMapSearchRectangleParam();
		//param.setQuery("美食");
		//param.setBounds("35.66597,139.797339,35.677669,139.813544");

		param.setScope("2");
//		param.setPage_num(1);
//		param.setPage_size(10);

		AMapResponse<AMapPlaceAbroadResultVO> response = searchRegionalism(param);
		//AMapResponse<AMapPlaceAbroadResultVO> response = searchCircum(param);
		//AMapResponse<AMapPlaceAbroadResultVO> response = searchRectangle(param);


		String message = response.getMessage();
		Integer status = response.getStatus();
		List<AMapPlaceAbroadResultVO> results = response.getResults();
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
