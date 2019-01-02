package cn.wyb.personal.service.bmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.wyb.personal.common.enums.BmapApiStatusCodeEnum;
import cn.wyb.personal.common.result.BmapLocationResponse;
import cn.wyb.personal.common.result.BmapResponse;
import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.utils.BmapUtil;
import cn.wyb.personal.model.param.*;
import cn.wyb.personal.model.vo.bmap.BmapPlaceAbroadResultVO;
import cn.wyb.personal.model.vo.bmap.Point;
import cn.wyb.personal.model.vo.bmap.PointStrVO;

/**
 * BmapServiceImpl: 百度地图API业务实现
 *
 * @author wangyibin
 * @date 2018/6/6 13:13
 * @see
 */
@Service
public class BmapServiceImpl implements BmapService {

    @Override
    public PointStrVO getPontByIp(String ip) {
        BmapLocationParam param = new BmapLocationParam(ip);
        BmapLocationResponse response = BmapUtil.searchLocationById(param);
        return response.getContent().getPoint();
    }

    @Override
    public String analyticGeographic84Coordinates(Point point) {
        BmapCoordsParam bmapCoordsParam = new BmapCoordsParam(point.toString());
        bmapCoordsParam.setFrom(1);
        List<PointStrVO> pointStrVOS = toBmapcoordinate(bmapCoordsParam);
        if (pointStrVOS != null) {
            PointStrVO pointStrVO = pointStrVOS.get(0);
            Point poi = BmapUtil.pointStr2Point(pointStrVO);
            return analyticGeographicCoordinates(poi);
        }
        return null;
    }

    @Override
    public List<PointStrVO> toBmapcoordinate(BmapCoordsParam param) {
        if (param == null || StringUtils.isBlank(param.getCoords())) {
            return null;
        }
        BmapResponse response = BmapUtil.toBmapcoordinate(param);
        if (BmapApiStatusCodeEnum.OK.getCode().equals(response.getStatus())) {
            List<PointStrVO> data = (List<PointStrVO>) response.getData();
            if (CollectionUtils.isNotEmpty(data)) {
                return data;
            }
        }
        return null;
    }

    @Override
    public CommResponse searchRegionalism(BmapSearchRegionalismParam param) {
        if (param != null && StringUtils.isNotBlank(param.getRegion())) {
            // ValidationUtils.validateEntityThrows(param);
            BmapResponse<BmapPlaceAbroadResultVO> bmapResponse = BmapUtil.searchRegionalism(param);
            return getReturnResponse(bmapResponse);
        }
        return CommResponse.failure("未查询到地址数据！");
    }

    @Override
    public CommResponse searchCircum(BmapSearchCircumParam param) {
        // ValidationUtils.validateEntityThrows(param);
        BmapResponse<BmapPlaceAbroadResultVO> bmapResponse = BmapUtil.searchCircum(param);
        return getReturnResponse(bmapResponse);
    }

    @Override
    public CommResponse searchRectangle(BmapSearchRectangleParam param) {
        // ValidationUtils.validateEntityThrows(param);
        BmapResponse<BmapPlaceAbroadResultVO> bmapResponse = BmapUtil.searchRectangle(param);
        return getReturnResponse(bmapResponse);
    }

    @Override
    public CommResponse searchSuggestion(BmapSearchSuggestionParam param) {
        // ValidationUtils.validateEntityThrows(param);
        BmapResponse<BmapPlaceAbroadResultVO> bmapResponse = BmapUtil.searchSuggestion(param);
        return getReturnResponse(bmapResponse);
    }

    @Override
    public CommResponse searchPlaceDetail(BmapPlaceDetailParam param) {
        // ValidationUtils.validateEntityThrows(param);
        if (StringUtils.isBlank(param.getUids())) {
            param.setUids(param.getUid());
        }
        BmapResponse<BmapPlaceAbroadResultVO> bmapResponse = BmapUtil.searchPlaceDetail(param);
        return getReturnResponse(bmapResponse);
    }

    @Override
    public List<PointStrVO> pointToBaidu(List<PointStrVO> jumoreGpsPoint, Integer from, int num) {
        if (jumoreGpsPoint != null && jumoreGpsPoint.size() > 0) {
            if (num < 1 || num > 99) {
                num = 99;
            }
            StringBuffer ps = new StringBuffer();
            Set<Integer> pointIndexSet = Sets.newHashSet();
            Set<Integer> wrongPointIndexSet = Sets.newHashSet();
            ArrayList<PointStrVO> baiduPoint = Lists.newArrayList();
            int countCall = 0;
            for (int i = 0; i < jumoreGpsPoint.size(); i++) {
                PointStrVO point = jumoreGpsPoint.get(i);
                if (StringUtils.isNotBlank(point.getX()) && StringUtils.isNotBlank(point.getY())) {
                    ps.append(point.toString()).append(";");// BizConsts.PUNCTUATION_SEMICOLON
                    pointIndexSet.add(i);
                } else {
                    wrongPointIndexSet.add(i);
                }
                if (jumoreGpsPoint.size() == i + 1 || (i != 0 && jumoreGpsPoint.size() % num == 0)) {
                    String s = ps.toString();
                    if (StringUtils.isBlank(s)) {
                        continue;
                    }
                    String psStr = s.substring(0, s.length() - 1);
                    List<PointStrVO> bps = toBmapcoordinate(new BmapCoordsParam(psStr, from));
                    if (CollectionUtils.isEmpty(bps)) {
                        i = i - num;
                    } else {
                        baiduPoint.addAll(bps);
                    }
                }
            }
            if (pointIndexSet.size() > 0) {
                ArrayList<Integer> pointIndexList = Lists.newArrayList(pointIndexSet);
                Collections.sort(pointIndexList);
                for (int i = 0; i < pointIndexList.size(); i++) {
                    PointStrVO baidu = baiduPoint.get(i);
                    jumoreGpsPoint.get(pointIndexList.get(i)).setX(baidu.getX());
                    jumoreGpsPoint.get(pointIndexList.get(i)).setY(baidu.getY());
                }
                return jumoreGpsPoint;
            }
            if (wrongPointIndexSet.size() > 0) {
                for (Integer index : wrongPointIndexSet) {
                    jumoreGpsPoint.get(index).setX(null);
                    jumoreGpsPoint.get(index).setY(null);
                }
            }
        }
        return jumoreGpsPoint;
    }

    @Override
    public String analyticGeographicCoordinates(@Valid Point point) {
        if (point != null) {
            BmapAnalyticGeographicCoordinatesParam param = new BmapAnalyticGeographicCoordinatesParam(
                    point.getLat() + "," + point.getLng());
            JSONObject response = BmapUtil.getApiJsonResponse(BmapUtil.BMAP_URL + BmapUtil.BMAP_GEOCODER_PORT_V2, param);
            Integer status = (Integer) response.get("status");
            if (BmapApiStatusCodeEnum.OK.getCode().equals(status)) {
                JSONObject result = (JSONObject) response.get("result");
                return (String) result.get("formatted_address");
            }
        }
        return null;
    }

    @Override
    public String analyticGeographicCoordinates(Point point, int from) {
        BmapCoordsParam bmapCoordsParam = new BmapCoordsParam(point.toString());
        bmapCoordsParam.setFrom(from);
        List<PointStrVO> pointStrVOS = toBmapcoordinate(bmapCoordsParam);
        if (pointStrVOS != null) {
            PointStrVO pointStrVO = pointStrVOS.get(0);
            Point poi = BmapUtil.pointStr2Point(pointStrVO);
            return analyticGeographicCoordinates(poi);
        }
        return null;
    }

    /**
     * getReturnResponse:解析百度webApi返回数据.
     *
     * @author wangyibin
     * @date 2018/6/15 9:20
     * @param result
     * @return
     */
    private CommResponse getReturnResponse(BmapResponse result) {
        if (result != null) {
            if (result.getStatus() == 0) {
                return CommResponse.success(result.getData());
            } else {
                return CommResponse.failure(result.getStatus().toString(), result.getMessage());
            }
        }
        return CommResponse.failure("未查询到地址数据！");
    }
}
