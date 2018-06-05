package cn.wyb.common.utils;

import cn.wyb.common.enums.AMapApiStatusCodeEnum;
import cn.wyb.common.result.AMapLocationResponse;
import cn.wyb.common.result.AMapResponse;
import cn.wyb.model.param.*;
import cn.wyb.model.vo.map.AMapPlaceAbroadResultVO;
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
	private static final String AMAP_LOCATION_IP_PORT = "/location/ip";
	private static final String POINT_SEARCH = "/search";
	private static final String POINT_DETAIL = "/detail";
	private static final String POINT_SUGGESTION = "/suggestion";

	/**
	 * @param param
	 * @return
	 * @Param2Map : param转Map
	 * @author wangyibin
	 * @date 2018/6/5 16:39
	 **/
	public static Map<String, String> Param2Map(AMapApiBaseParam param) {
		HashMap<String, String> map = Maps.newHashMap();
		String output = null;
		if (param != null) {
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
			output = param.getOutput();
		}
		if (StringUtils.isBlank(output)) {
			output = "json";
		}
		output = "xml".equals(output.toLowerCase()) ? "xml" : "json";
		map.put("output", output);
		map.put("ak", AMAP_AK);
		return map;
	}

	/**
	 * @param url
	 * @param param
	 * @return
	 * @getAMapAPIResponse : 获取百度地图API响应
	 * @author wangyibin
	 * @date 2018/6/5 16:40
	 **/
	public static String getAMapAPIResponse(String url, AMapApiBaseParam param) {
		Map<String, String> map = Param2Map(param);
		String s = HttpUtil.sendGet(url, map);
		System.out.println(s);
		if (StringUtils.isBlank(s)) {
			return null;
		}
		return s;
	}

	public static JSONObject getAMapAPIJsonResponse(String url, AMapApiBaseParam param) {
		return JSONObject.parseObject(getAMapAPIResponse(url, param));
	}

	/**
	 * @getSearchResponse : 获取搜索结果
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:42
	 * @param port
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> getSearchResponse(String port, AMapApiBaseParam param) {
		String url = AMAP_ROOT_URL + AMAP_PLACE_ABROAD_PORT + port;
		JSONObject jsonObject = getAMapAPIJsonResponse(url, param);
		AMapResponse<AMapPlaceAbroadResultVO> response = jsonObject.toJavaObject(AMapResponse.class);
		if (AMapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
			JSONArray results = jsonObject.getJSONArray("result");
			if (results != null && !results.isEmpty()) {
				ArrayList<AMapPlaceAbroadResultVO> resultVOS = new ArrayList<>();
				for (int i = 0; i < results.size(); i++) {
					AMapPlaceAbroadResultVO vo = results.getJSONObject(i).toJavaObject(AMapPlaceAbroadResultVO.class);
					resultVOS.add(vo);
				}
				response.setData(resultVOS);
			}
		}
		return response;
	}

	/**
	 * @getSearchPlaceResponse : 地点检索查询
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> getSearchPlaceResponse(AMapSearchBaseParam param) {
		AMapResponse<AMapPlaceAbroadResultVO> response = getSearchResponse(POINT_SEARCH, param);
		return response;
	}

	/**
	 * @searchPlaceDetail : 地点详情检索
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> searchPlaceDetail(AMapPlaceDetailParam param) {
		AMapResponse<AMapPlaceAbroadResultVO> response = getSearchResponse(POINT_DETAIL, param);
		return response;
	}

	/**
	 * @searchRegionalism : 行政区划区域检索
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> searchRegionalism(AMapSearchRegionalismParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @searchCircum : 周边检索
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> searchCircum(AMapSearchCircumParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @searchRectangle : 矩形区域检索
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> searchRectangle(AMapSearchRectangleParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @searchSuggestion : 地点输入提示
	 *
	 * @author wangyibin
	 * @date 2018/6/5 16:44
	 * @param param
	 * @return
	 **/
	public static AMapResponse<AMapPlaceAbroadResultVO> searchSuggestion(AMapSearchSuggestionParam param) {
		return getSearchResponse(POINT_SUGGESTION, param);
	}

	/**
	 * @param param
	 * @return
	 * @searchLocationById : IP定位
	 * @author wangyibin
	 * @date 2018/6/5 16:44
	 **/
	public static AMapLocationResponse searchLocationById(AMapLocationParam param) {
		String url = AMAP_ROOT_URL + AMAP_LOCATION_IP_PORT;
		JSONObject jsonObject = getAMapAPIJsonResponse(url, param);
		AMapLocationResponse response = jsonObject.toJavaObject(AMapLocationResponse.class);
		return response;
	}


	public static void main(String[] args) {

		//AMapSearchRegionalismParam param = new AMapSearchRegionalismParam();
		//param.setQuery("华尔街");
		//param.setRegion("纽约");


		//AMapSearchCircumParam param = new AMapSearchCircumParam();
		//param.setQuery("寿司");
		//param.setLocation("35.711343,139.767111");

		//AMapSearchRectangleParam param = new AMapSearchRectangleParam();
		//param.setQuery("美食");
		//param.setBounds("35.66597,139.797339,35.677669,139.813544");

		//param.setScope("2");
//		param.setPage_num(1);
//		param.setPage_size(10);

		//AMapResponse<AMapPlaceAbroadResultVO> response = searchRegionalism(param);
		//AMapResponse<AMapPlaceAbroadResultVO> response = searchCircum(param);
		//AMapResponse<AMapPlaceAbroadResultVO> response = searchRectangle(param);


//		String message = response.getMessage();
//		Integer status = response.getStatus();
//		List<AMapPlaceAbroadResultVO> results = response.getResults();
//		String uid = results.get(3).getUid();
//		AMapPlaceDetailParam detailParam = new AMapPlaceDetailParam();
//		detailParam.setUid(uid);
//		detailParam.setUids(uid);
//		detailParam.setScope("1");
//		AMapResponse<AMapPlaceAbroadResultVO> detailResponse = searchPlaceDetail(detailParam);
//		detailResponse.getStatus();
//		detailResponse.getMessage();

		/*AMapSearchRegionalismParam param = new AMapSearchRegionalismParam();
		param.setQuery("首尔");
		param.setRegion("首尔");
		String url = AMAP_ROOT_URL + AMAP_PLACE_ABROAD_PORT + POINT_SUGGESTION;
		JSONObject jsonObject = getAMapAPIResponse(url, param);
		String s = jsonObject.toJSONString();
		System.out.println(s);*/
		AMapLocationResponse response = searchLocationById(null);
		System.out.printf(response.getAddress());
		System.out.println(response.getContent());

	}

}
