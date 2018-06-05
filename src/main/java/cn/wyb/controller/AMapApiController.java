package cn.wyb.controller;

import cn.wyb.common.result.AMapResponse;
import cn.wyb.common.utils.AMapUtil;
import cn.wyb.model.param.*;
import cn.wyb.model.vo.map.AMapPlaceAbroadResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AMapApiController: 百度地图地址检索API
 *
 * @author wangyibin
 * @date 2018/6/1 14:43
 * @see
 */
@Api(value = "aMapApiController", description = "百度地图地址检索API")
@Controller
@RequestMapping("/v1/amap")
public class AMapApiController {

	@ApiOperation(value = "行政区划区域检索", notes = "行政区划区域检索", response = AMapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchRegionalism", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRegionalism(AMapSearchRegionalismParam param) {
		return AMapUtil.searchRegionalism(param);
	}

	@ApiOperation(value = "周边检索", notes = "周边检索", response = AMapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchCircum", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchCircum(AMapSearchCircumParam param) {
		return AMapUtil.searchCircum(param);
	}

	@ApiOperation(value = "矩形区域检索", notes = "矩形区域检索", response = AMapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchRectangle", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRectangle(AMapSearchRectangleParam param) {
		return AMapUtil.searchRectangle(param);
	}

	@ApiOperation(value = "地点详情检索", notes = "地点详情检索", response = AMapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchPlaceDetail", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRegionalism(AMapPlaceDetailParam param) {
		return AMapUtil.searchPlaceDetail(param);
	}

	@ApiOperation(value = "地点输入提示", notes = "地点输入提示", response = AMapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchSuggestion", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchSuggestion(AMapSearchSuggestionParam param) {
		param.setRegion(param.getQuery());
		return AMapUtil.searchSuggestion(param);
	}

}
