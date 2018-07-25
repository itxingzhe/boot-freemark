package cn.wyb.personal.common.utils;

import cn.wyb.personal.common.enums.AMapApiStatusCodeEnum;
import cn.wyb.personal.common.result.BmapLocationResponse;
import cn.wyb.personal.common.result.BmapResponse;
import cn.wyb.personal.model.param.*;
import cn.wyb.personal.model.vo.map.BmapPlaceAbroadResultVO;
import cn.wyb.personal.model.vo.map.PointStrVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class BmapUtil {

	public static final String BMAP_AK = "ev82Imq86gQmRRZT5Chobk35KKPPh3NB";
	private static final String BMAP_URL = "http://api.map.baidu.com";
	private static final String BMAP_PLACE_ABROAD_PORT = "/place_abroad/v1";
	private static final String BMAP_LOCATION_IP_PORT = "/location/ip";
	private static final String BMAP_GEOCONV_PORT = "/geoconv/v1/";
	private static final String POINT_SEARCH = "/search";
	private static final String POINT_DETAIL = "/detail";
	private static final String POINT_SUGGESTION = "/suggestion";
	private static final Double RE = 6378137.0;

	/**
	 * @param param
	 * @return
	 * @Param2Map : param转Map
	 * @author wangyibin
	 * @date 2018/6/5 16:39
	 **/
	public static Map<String, String> Param2Map(BmapApiBaseParam param) {
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
		map.put("ak", BMAP_AK);
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
	public static String getApiResponse(String url, BmapApiBaseParam param) {
		Map<String, String> map = Param2Map(param);
		String s = HttpUtil.sendGet(url, map);
		if (StringUtils.isBlank(s)) {
			return null;
		}
		return s;
	}

	public static JSONObject getApiJsonResponse(String url, BmapApiBaseParam param) {
		return JSONObject.parseObject(getApiResponse(url, param));
	}

	/**
	 * @param port
	 * @param param
	 * @return
	 * @getSearchResponse : 获取搜索结果
	 * @author wangyibin
	 * @date 2018/6/5 16:42
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> getSearchResponse(String port, BmapApiBaseParam param) {
		String url = BMAP_URL + BMAP_PLACE_ABROAD_PORT + port;
		JSONObject jsonObject = getApiJsonResponse(url, param);
		BmapResponse<BmapPlaceAbroadResultVO> response = jsonObject.toJavaObject(BmapResponse.class);
		if (AMapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
			JSONArray results = jsonObject.getJSONArray("results");
			if (results == null) {
				results = jsonObject.getJSONArray("result");
			}
			if (results != null && !results.isEmpty()) {
				ArrayList<BmapPlaceAbroadResultVO> resultVOS = new ArrayList<>();
				for (int i = 0; i < results.size(); i++) {
					BmapPlaceAbroadResultVO vo = results.getJSONObject(i).toJavaObject(BmapPlaceAbroadResultVO.class);
					resultVOS.add(vo);
				}
				response.setData(resultVOS);
			}
		}
		return response;
	}

	/**
	 * @param param
	 * @return
	 * @getSearchPlaceResponse : 地点检索查询
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> getSearchPlaceResponse(BmapSearchBaseParam param) {
		BmapResponse<BmapPlaceAbroadResultVO> response = getSearchResponse(POINT_SEARCH, param);
		return response;
	}

	/**
	 * @param param
	 * @return
	 * @searchPlaceDetail : 地点详情检索
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> searchPlaceDetail(BmapPlaceDetailParam param) {
		BmapResponse<BmapPlaceAbroadResultVO> response = getSearchResponse(POINT_DETAIL, param);
		return response;
	}

	/**
	 * @param param
	 * @return
	 * @searchRegionalism : 行政区划区域检索
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> searchRegionalism(BmapSearchRegionalismParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @param param
	 * @return
	 * @searchCircum : 周边检索
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> searchCircum(BmapSearchCircumParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @param param
	 * @return
	 * @searchRectangle : 矩形区域检索
	 * @author wangyibin
	 * @date 2018/6/5 16:45
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> searchRectangle(BmapSearchRectangleParam param) {
		return getSearchPlaceResponse(param);
	}

	/**
	 * @param param
	 * @return
	 * @searchSuggestion : 地点输入提示
	 * @author wangyibin
	 * @date 2018/6/5 16:44
	 **/
	public static BmapResponse<BmapPlaceAbroadResultVO> searchSuggestion(BmapSearchSuggestionParam param) {
		return getSearchResponse(POINT_SUGGESTION, param);
	}

	/**
	 * @param param
	 * @return
	 * @searchLocationById : IP定位
	 * @author wangyibin
	 * @date 2018/6/5 16:44
	 **/
	public static BmapLocationResponse searchLocationById(BmapLocationParam param) {
		String url = BMAP_URL + BMAP_LOCATION_IP_PORT;
		JSONObject jsonObject = getApiJsonResponse(url, param);
		BmapLocationResponse response = jsonObject.toJavaObject(BmapLocationResponse.class);
		return response;
	}

	/**
	 * toBmapcoordinate:坐标转换为百度地图坐标
	 *
	 * @param param
	 * @return
	 * @author wangyibin
	 * @date 2018/6/8 13:08
	 */
	public static BmapResponse toBmapcoordinate(BmapCoordsParam param) {
		String url = BMAP_URL + BMAP_GEOCONV_PORT;
		JSONObject jsonObject = getApiJsonResponse(url, param);
		BmapResponse response = jsonObject.toJavaObject(BmapResponse.class);
		if (AMapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
			JSONArray results = jsonObject.getJSONArray("result");
			if (results != null && !results.isEmpty()) {
				ArrayList<PointStrVO> resultVOS = new ArrayList<>();
				for (int i = 0; i < results.size(); i++) {
					PointStrVO vo = results.getJSONObject(i).toJavaObject(PointStrVO.class);
					resultVOS.add(vo);
				}
				response.setData(resultVOS);
			}
		}
		return response;
	}

	/**
	 * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
	 * 参数为String类型
	 *
	 * @param lat1Str 用户经度
	 * @param lng1Str 用户纬度
	 * @param lat2Str 商家经度
	 * @param lng2Str 商家纬度
	 * @return
	 */
	public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) {
		Double lat1 = Double.parseDouble(lat1Str);
		Double lng1 = Double.parseDouble(lng1Str);
		Double lat2 = Double.parseDouble(lat2Str);
		Double lng2 = Double.parseDouble(lng2Str);
		double patm = 2;
		double radLat1 = Math.toRadians(lat1);
		double radLat2 = Math.toRadians(lat2);
		double difference = radLat1 - radLat2;
		double mdifference = Math.toRadians(lng1) - Math.toRadians(lng2);
		double distance = patm * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / patm), patm)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(mdifference / patm), patm)));
		distance = distance * RE;
		String distanceStr = String.valueOf(distance);
		return distanceStr;
	}

	/**
	 * 获取当前用户一定距离以内的经纬度值
	 * 单位米 return minLat
	 * 最小经度 minLng
	 * 最小纬度 maxLat
	 * 最大经度 maxLng
	 * 最大纬度 minLat
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Map getAround(String latStr, String lngStr, String raidus) {
		Map map = new HashMap();
		Double latitude = Double.parseDouble(latStr);// 传值给经度
		Double longitude = Double.parseDouble(lngStr);// 传值给纬度
		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = Double.parseDouble(raidus);

		Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180)) + "").replace("-", ""));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		//获取最小经度
		Double minLat = longitude - radiusLng;
		// 获取最大经度
		Double maxLat = longitude + radiusLng;
		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLng = latitude - radiusLat;
		// 获取最大纬度
		Double maxLng = latitude + radiusLat;

		map.put("minLat", minLat + "");
		map.put("maxLat", maxLat + "");
		map.put("minLng", minLng + "");
		map.put("maxLng", maxLng + "");
		return map;
	}

}
