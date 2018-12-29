package cn.wyb.personal.common.utils;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import cn.wyb.personal.common.enums.BmapApiStatusCodeEnum;
import cn.wyb.personal.common.result.BmapLocationResponse;
import cn.wyb.personal.common.result.BmapResponse;
import cn.wyb.personal.model.param.*;
import cn.wyb.personal.model.vo.bmap.BmapPlaceAbroadResultVO;
import cn.wyb.personal.model.vo.bmap.Point;
import cn.wyb.personal.model.vo.bmap.PointStrVO;

public class BmapUtil {

    public static final String  BMAP_AK                = "ev82Imq86gQmRRZT5Chobk35KKPPh3NB";
    public static final String  BMAP_URL               = "http://api.map.baidu.com";
    public static final String  BMAP_PLACE_ABROAD_PORT = "/place_abroad/v1";
    public static final String  BMAP_LOCATION_IP_PORT  = "/location/ip";
    public static final String  BMAP_GEOCONV_PORT_V1   = "/geoconv/v1/";
    public static final String  BMAP_GEOCODER_PORT_V2  = "/geocoder/v2/";
    public static final String  POINT_SEARCH           = "/search";
    public static final String  POINT_DETAIL           = "/detail";
    public static final String  POINT_SUGGESTION       = "/suggestion";
    private static final Double RE                     = 6378137.0;

    // private static final LogHelper LOGGER = LogHelper.getLogger(BmapUtil.class);

    /**
     * @Param2Map : param转Map
     *
     * @author wangyibin
     * @date 2018/6/5 16:39
     * @param param
     * @return
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
     * @getAMapAPIResponse : 获取百度地图API响应
     *
     * @author wangyibin
     * @date 2018/6/5 16:40
     * @param url
     * @param param
     * @return
     **/
    public static String getApiResponse(String url, BmapApiBaseParam param) {
        Map<String, String> map = Param2Map(param);
        String s = null;
        try {
            s = HttpUtil.sendGet(url, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static JSONObject getApiJsonResponse(String url, BmapApiBaseParam param) {
        return JSONObject.parseObject(getApiResponse(url, param));
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
    public static BmapResponse<BmapPlaceAbroadResultVO> getSearchResponse(String port, BmapApiBaseParam param) {
        String url = BMAP_URL + BMAP_PLACE_ABROAD_PORT + port;
        JSONObject jsonObject = getApiJsonResponse(url, param);
        BmapResponse<BmapPlaceAbroadResultVO> response = jsonObject.toJavaObject(BmapResponse.class);
        if (BmapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
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
        } else {
            System.out.println("百度API请求响应异常 status：" + response.getStatus() + "message：" + response.getMessage());
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
    public static BmapResponse<BmapPlaceAbroadResultVO> getSearchPlaceResponse(BmapSearchBaseParam param) {
        BmapResponse<BmapPlaceAbroadResultVO> response = getSearchResponse(POINT_SEARCH, param);
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
    public static BmapResponse<BmapPlaceAbroadResultVO> searchPlaceDetail(BmapPlaceDetailParam param) {
        BmapResponse<BmapPlaceAbroadResultVO> response = getSearchResponse(POINT_DETAIL, param);
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
    public static BmapResponse<BmapPlaceAbroadResultVO> searchRegionalism(BmapSearchRegionalismParam param) {
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
    public static BmapResponse<BmapPlaceAbroadResultVO> searchCircum(BmapSearchCircumParam param) {
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
    public static BmapResponse<BmapPlaceAbroadResultVO> searchRectangle(BmapSearchRectangleParam param) {
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
    public static BmapResponse<BmapPlaceAbroadResultVO> searchSuggestion(BmapSearchSuggestionParam param) {
        return getSearchResponse(POINT_SUGGESTION, param);
    }

    /**
     * @searchLocationById : IP定位
     *
     * @author wangyibin
     * @date 2018/6/5 16:44
     * @param param
     * @return
     **/
    public static BmapLocationResponse searchLocationById(BmapLocationParam param) {
        String url = BMAP_URL + BMAP_LOCATION_IP_PORT;
        JSONObject jsonObject = getApiJsonResponse(url, param);
        BmapLocationResponse response = jsonObject.toJavaObject(BmapLocationResponse.class);
        if (BmapApiStatusCodeEnum.OK.getCode() != response.getStatus()) {
            System.out.println(
                    "百度API请求响应异常 status：" + response.getStatus() + "message：" + BmapApiStatusCodeEnum.getMessage(response.getStatus()));
        }
        return response;
    }

    /**
     * toBmapcoordinate:坐标转换为百度地图坐标
     *
     * @author wangyibin
     * @date 2018/6/8 13:08
     * @param param
     * @return
     */
    public static BmapResponse toBmapcoordinate(BmapCoordsParam param) {
        String url = BMAP_URL + BMAP_GEOCONV_PORT_V1;
        JSONObject jsonObject = getApiJsonResponse(url, param);
        BmapResponse response = jsonObject.toJavaObject(BmapResponse.class);
        if (BmapApiStatusCodeEnum.OK.getCode() == response.getStatus()) {
            JSONArray results = jsonObject.getJSONArray("result");
            if (results != null && !results.isEmpty()) {
                ArrayList<PointStrVO> resultVOS = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    PointStrVO vo = results.getJSONObject(i).toJavaObject(PointStrVO.class);
                    resultVOS.add(vo);
                }
                response.setData(resultVOS);
            }
        } else {
            System.out.println("百度API请求响应异常 status：" + response.getStatus() + "message：" + response.getMessage());
        }
        return response;
    }

    public static Point pointStr2Point(PointStrVO vo) {
        if (vo != null) {
            Point point = new Point();
            point.setLat(Float.parseFloat(vo.getY()));
            point.setLng(Float.parseFloat(vo.getX()));
            return point;
        }
        return null;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM） 参数为String类型
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
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / patm), patm)));
        distance = distance * RE;
        String distanceStr = String.valueOf(distance);
        return distanceStr;
    }

    /**
     * 获取当前用户一定距离以内的经纬度值 单位米 return minLat 最小经度 minLng 最小纬度 maxLat 最大经度 maxLng 最大纬度
     * minLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);

        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180)) + "").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        // 获取最小经度
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
