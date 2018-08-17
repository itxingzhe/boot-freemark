package cn.wyb.personal.controller;

import cn.wyb.personal.model.vo.role.RoleVO;
import cn.wyb.personal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * RoleController: 角色控制层.
 *
 * @author wangyibin
 * @date 2018/8/14 14:37
 * @see
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/manager")
	@ResponseBody
	public ModelAndView manager(Long id) {
		ModelAndView modelAndView = new ModelAndView("/role/manager");
		modelAndView.addObject("userId", id);
		return modelAndView;
	}

	@RequestMapping("/listData")
	@ResponseBody
	public List<RoleVO> listData() {
		return roleService.queryListData();
	}
}
