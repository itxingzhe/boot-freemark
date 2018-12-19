package cn.wyb.personal.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.utils.HttpUtil;
import cn.wyb.personal.common.utils.UserUtils;
import cn.wyb.personal.handler.MyWebSocketHandler;
import cn.wyb.personal.model.po.UserPO;

/**
 * Create Time: 2018年04月26日 13:09
 * C@author wyb
 **/
@Controller
public class CommonController {

    // 注入发送消息的Handler类
    @Resource(name = "myHandler")
    private WebSocketHandler myHandler;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public CommResponse sendMessage(String messageText) {
        MyWebSocketHandler webSocketHandler = (MyWebSocketHandler) myHandler;
        TextMessage textMessage = new TextMessage(messageText);
        boolean hasSend = webSocketHandler.sendMessageToUser(UserUtils.getLoginUserId(), textMessage);
        System.out.println(hasSend);
        if (hasSend) {
            return CommResponse.success("成功接收");
        } else {
            return CommResponse.failure("接收失败");
        }
    }

	@RequestMapping("/")
	public String init(Model m, HttpServletRequest request) {
		//AtomicInteger.class;
		m.addAttribute("username", "张三");
		UserPO user = new UserPO();
		user.setUname("张三");
		user.setAddress("是大法官好sdfdsf,hello");
		String s = JSON.toJSONString(user);
		System.out.println(s);
		String s1 = StringEscapeUtils.escapeJson(s);
		System.out.println(s1);
		m.addAttribute("escapeJson", s1);
		m.addAttribute("contextPath", request.getServletContext().getContextPath());
		m.addAttribute("requestURI", request.getRequestURI());
		m.addAttribute("servletPath", request.getServletPath());
		String realPath = request.getServletContext().getRealPath("/");
		String realPath1 = request.getServletContext().getRealPath("static/img");
		String realPath3 = request.getServletContext().getRealPath("img");
		try {
			File file = new File("src/main/resources/static/img/a.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(out);
			objOut.writeObject(user);
			objOut.flush();
			objOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

    @RequestMapping(value = "/common/bmap", method = RequestMethod.GET)
	public String toMap() {
        return "/map/bmap";
	}

    @RequestMapping(value = "/common/bmap2", method = RequestMethod.GET)
    public String toBmap() {
        return "/map/bmap2";
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

    @RequestMapping(value = "common/bmapApi", method = RequestMethod.GET)
    @ResponseBody
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

	@RequestMapping("common/upload")
	@ResponseBody
	public ModelAndView toUpload() {
		return new ModelAndView("/upload/upload");
	}

}
