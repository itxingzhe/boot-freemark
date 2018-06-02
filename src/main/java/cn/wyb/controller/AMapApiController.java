package cn.wyb.controller;

import cn.wyb.common.utils.AMapUtil;
import cn.wyb.model.param.AMapPlaceDetailParam;
import cn.wyb.model.param.AMapSearchCircumParam;
import cn.wyb.model.param.AMapSearchRectangleParam;
import cn.wyb.model.param.AMapSearchRegionalismParam;
import cn.wyb.model.vo.AMapResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("amap")
public class AMapApiController {

	@RequestMapping(value = "/searchRegionalism", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRegionalism(AMapSearchRegionalismParam param) {
		return AMapUtil.searchRegionalism(param);
	}

	@RequestMapping(value = "/searchCircum", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchCircum(AMapSearchCircumParam param) {
		return AMapUtil.searchCircum(param);
	}

	@RequestMapping(value = "/searchRectangle", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRectangle(AMapSearchRectangleParam param) {
		return AMapUtil.searchRectangle(param);
	}

	@RequestMapping(value = "/searchPlaceDetail", method = RequestMethod.GET)
	@ResponseBody
	public AMapResponse getSearchRegionalism(AMapPlaceDetailParam param) {
		return AMapUtil.searchPlaceDetail(param);
	}


}
