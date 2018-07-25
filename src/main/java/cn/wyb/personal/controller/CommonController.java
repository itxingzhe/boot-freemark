package cn.wyb.personal.controller;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.utils.HttpUtil;
import com.google.common.collect.Maps;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Create Time: 2018年04月26日 13:09
 * C@author wyb
 **/
@Controller
public class CommonController {

	@RequestMapping("/")
	public String init(Model m) {
		m.addAttribute("username", "张三");
		return "index";
	}

	@RequestMapping("common/bmap")
	public String toMap() {
		return "/map/bmap";
	}

	@RequestMapping("common/ajaxFileUpload")
	@ResponseBody
	public CommResponse ajaxFileUpload(@RequestParam(value = "uploadFile") MultipartFile uploadFile) {
		String name = uploadFile.getName();
		String contentType = uploadFile.getContentType();
		String originalFilename = uploadFile.getOriginalFilename();
		return CommResponse.success(name + "-" + contentType + "-" + originalFilename);
	}

	@RequestMapping("common/fileUpload")
	@ResponseBody
	public CommResponse fileUpload(@RequestParam(value = "uploadFile") MultipartFile uploadFile) {
		String name = uploadFile.getName();
		String contentType = uploadFile.getContentType();
		String originalFilename = uploadFile.getOriginalFilename();
		return CommResponse.success(name + "-" + contentType + "-" + originalFilename);
	}

	@RequestMapping("common/bmapApi")
	public ModelAndView toMapApi() throws Exception {

		HashMap<String, String> map = Maps.newHashMap();
		map.put("query", "华尔街");
		map.put("region", "纽约");
		map.put("output", "json");
		map.put("ak", HttpUtil.AMAP_AK);
		CloseableHttpResponse s = HttpUtil.sendAmapGet(HttpUtil.AMAP_SEARCH_URL, map);
		ModelAndView mv = new ModelAndView();
		mv.addObject(s);
		return mv;

	}

	@RequestMapping("common/httpCollectTest")
	public String httpCollectTest() {
		System.out.println("连接了一次");
		int a = 1 / 0;
		return "" + a;
	}

	@RequestMapping("common/test")
	public ModelAndView myTest(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		request.setAttribute("basePath", basePath);
		ModelAndView mv = new ModelAndView("/test/test");
		mv.addObject("userId", null);
		return mv;
	}

	@RequestMapping("common/amap")
	public ModelAndView showAmap(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("map/amap");
		return mv;
	}

}
