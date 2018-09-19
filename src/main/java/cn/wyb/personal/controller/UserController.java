package cn.wyb.personal.controller;

import cn.wyb.personal.annotation.MethodLog;
import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.common.result.PageResult;
import cn.wyb.personal.model.param.UserParam;
import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Create Time: 2018年04月26日 13:06
 * C@author wyb
 **/
@Controller
@RequestMapping("/user")
public class UserController {

	//通过类型注入
	@Autowired
	// 通过名称注入
	//@Qualifier("userService2")
	private UserService userService;

	//通过名称注入
	@Resource(name = "userService2")
	private UserService userService2;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init() {
		return new ModelAndView("/user/list");
	}

	@RequestMapping("/queryData")
	@ResponseBody
	public PageResult<UserPO> listData(UserParam param) {
		userService.toInterface();
		userService2.toInterface();
		return userService.listData(param);
	}

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin(Model m) {
		m.addAttribute("user", userService.getUser(1));
		m.addAttribute("token", "user-token");
		return "user/toLogin";
	}

	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public String login(UserPO user, HttpSession session) {

		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		Subject subject = SecurityUtils.getSubject();
		//完成登录
		subject.login(usernamePasswordToken);
			UserPO userIn = (UserPO) subject.getPrincipal();
		session.setAttribute("userInfo", userIn);
			return "forward:/";

	}

	@RequestMapping(value = "toRegister", method = RequestMethod.GET)
	public String register(Model m) {
		m.addAttribute("token", "user-token");
		return "user/register";
	}

	@RequestMapping(value = "toUpdate", method = RequestMethod.GET)
	public String toUpdate(Model m, Integer id) {
		UserPO user = userService.getUser(id);
		m.addAttribute("user", user);
		return "user/toUpdate";
	}

	//注解的使用
	@MethodLog(remark = "新增用户")
	@RequiresRoles("admin")
	@RequiresPermissions("user_add")
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	@ResponseBody
	public CommResponse addUser(UserPO user) {
		Integer row = userService.save(user);
		return CommResponse.success(row);
	}

	//注解的使用
	@RequiresRoles("admin")
	@RequiresPermissions("update")
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	@ResponseBody
	public CommResponse updateUser(UserPO user) {
		Integer row = userService.update(user);
		return CommResponse.success(row);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logOut(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		session.getAttribute("user");
		subject.logout();
		return new ModelAndView("user/toLogin");
	}
}
