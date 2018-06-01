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

@Controller
public class AMapApiController {

	@RequestMapping(value = "searchRegionalism", method = RequestMethod.GET)
	public AMapResponse getSearchRegionalism(AMapSearchRegionalismParam param) {
		return AMapUtil.searchRegionalism(param);
	}

	@RequestMapping(value = "searchCircum", method = RequestMethod.GET)
	public AMapResponse getSearchCircum(AMapSearchCircumParam param) {
		return AMapUtil.searchCircum(param);
	}

	@RequestMapping(value = "SearchRectangle", method = RequestMethod.GET)
	public AMapResponse getSearchRectangle(AMapSearchRectangleParam param) {
		return AMapUtil.searchRectangle(param);
	}

	@RequestMapping(value = "SearchPlaceDetail", method = RequestMethod.GET)
	public AMapResponse getSearchRegionalism(AMapPlaceDetailParam param) {
		return AMapUtil.searchPlaceDetail(param);
	}


}
