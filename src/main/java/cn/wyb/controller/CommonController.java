package cn.wyb.controller;

import cn.wyb.model.vo.CommResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping("common/amap")
	public String toMap() {
		return "/map/amap";
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
}
