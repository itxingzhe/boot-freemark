package cn.wyb.personal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wyb.personal.common.result.CommResponse;
import cn.wyb.personal.model.vo.Authority.AuthorityVO;
import cn.wyb.personal.service.user.AuthorityService;

/**
 * AuthorityController: 权限控制层.
 *
 * @author wangyibin
 * @date 2018/8/14 15:19
 * @see
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("/listData")
	@ResponseBody
	public List<AuthorityVO> listData() {
		return authorityService.queryData();
	}

	@RequestMapping("/saveRoleModule")
	@ResponseBody
    public CommResponse saveRoleModule(Integer roleId, @RequestParam(value = "moduleIds[]") Integer[] moduleIds) {
		authorityService.saveModuleAndRole(roleId, moduleIds);
		return CommResponse.success();
	}

}
