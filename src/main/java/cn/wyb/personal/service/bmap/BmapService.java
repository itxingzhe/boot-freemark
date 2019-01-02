package cn.wyb.personal.service.bmap;

import java.util.List;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.model.param.*;
import cn.wyb.personal.model.vo.bmap.Point;
import cn.wyb.personal.model.vo.bmap.PointStrVO;

/**
 * BmapService: 百度地图业务类
 *
 * @author wangyibin
 * @date 2018/6/6 13:10
 * @see
 */
public interface BmapService {

    // ip定位
    PointStrVO getPontByIp(String ip);

    /**
     * 解析经纬度
     *
     * @author wangyibin
     * @date 2018/11/20 13:09
     * @param point
     * @return
     */
    String analyticGeographic84Coordinates(Point point);

    /**
     * 百度地图经纬度解析经纬度
     *
     * @author wangyibin
     * @date 2018/11/20 13:09
     * @param point
     * @return
     */
    String analyticGeographicCoordinates(Point point);

    /**
     * 解析经纬度
     *
     * @author wangyibin
     * @date 2018/11/20 13:09
     * @param point
     * @param from 源坐标类型： 1：GPS设备获取的角度坐标，wgs84坐标;
     *            2：GPS获取的米制坐标、sogou地图所用坐标;3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局（gcj02）坐标;4：3中列表地图坐标对应的米制坐标;
     *            5：百度地图采用的经纬度坐标; 6：百度地图采用的米制坐标; 7：mapbar地图坐标; 8：51地图坐标
     * @return
     */
    String analyticGeographicCoordinates(Point point, int from);

    /**
     * toBmapcoordinate:坐标转换为百度地图坐标.
     *
     * @author wangyibin
     * @date 2018/6/15 9:04
     * @param param
     * @return
     */
    List<PointStrVO> toBmapcoordinate(BmapCoordsParam param);

    /**
     * searchRegionalism:地址检索
     *
     * @author wangyibin
     * @date 2018/6/15 9:05
     * @param param
     * @return
     */
    CommResponse searchRegionalism(BmapSearchRegionalismParam param);

    /**
     * searchCircum:指定地址周边地址检索.
     *
     * @author wangyibin
     * @date 2018/6/15 9:07
     * @param param
     * @return
     */
    CommResponse searchCircum(BmapSearchCircumParam param);

    /**
     * searchRectangle:指定矩形区域检索.
     *
     * @author wangyibin
     * @date 2018/6/15 9:08
     * @param param
     * @return
     */
    CommResponse searchRectangle(BmapSearchRectangleParam param);

    /**
     * searchSuggestion:地点输入提示.
     *
     * @author wangyibin
     * @date 2018/6/15 9:09
     * @param param
     * @return
     */
    CommResponse searchSuggestion(BmapSearchSuggestionParam param);

    /**
     * searchSuggestion:百度返回地址的详细信息查询.
     *
     * @author wangyibin
     * @date 2018/6/15 9:09
     * @param param
     * @return
     */
    CommResponse searchPlaceDetail(BmapPlaceDetailParam param);

    List<PointStrVO> pointToBaidu(List<PointStrVO> jumoreGpsPoint, Integer from, int num);
}
