package cn.wyb.controller;

import cn.wyb.common.result.BmapResponse;
import cn.wyb.common.utils.BmapUtil;
import cn.wyb.model.param.*;
import cn.wyb.model.vo.map.BmapPlaceAbroadResultVO;
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
public class BmapApiController {

	@ApiOperation(value = "行政区划区域检索", notes = "行政区划区域检索", response = BmapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchRegionalism", method = RequestMethod.GET)
	@ResponseBody
	public BmapResponse getSearchRegionalism(BmapSearchRegionalismParam param) {
		return BmapUtil.searchRegionalism(param);
	}

	@ApiOperation(value = "周边检索", notes = "周边检索", response = BmapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchCircum", method = RequestMethod.GET)
	@ResponseBody
	public BmapResponse getSearchCircum(BmapSearchCircumParam param) {
		return BmapUtil.searchCircum(param);
	}

	@ApiOperation(value = "矩形区域检索", notes = "矩形区域检索", response = BmapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchRectangle", method = RequestMethod.GET)
	@ResponseBody
	public BmapResponse getSearchRectangle(BmapSearchRectangleParam param) {
		return BmapUtil.searchRectangle(param);
	}

	@ApiOperation(value = "地点详情检索", notes = "地点详情检索", response = BmapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchPlaceDetail", method = RequestMethod.GET)
	@ResponseBody
	public BmapResponse getSearchRegionalism(BmapPlaceDetailParam param) {
		return BmapUtil.searchPlaceDetail(param);
	}

	@ApiOperation(value = "地点输入提示", notes = "地点输入提示", response = BmapPlaceAbroadResultVO.class)
	@RequestMapping(value = "/searchSuggestion", method = RequestMethod.GET)
	@ResponseBody
	public BmapResponse getSearchSuggestion(BmapSearchSuggestionParam param) {
		param.setRegion(param.getQuery());
		return BmapUtil.searchSuggestion(param);
	}

}
