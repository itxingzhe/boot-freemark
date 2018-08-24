package cn.wyb.personal.service.impl;

import cn.wyb.personal.common.result.PageResult;
import cn.wyb.personal.dao.UserMapper;
import cn.wyb.personal.model.param.UserParam;
import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create Time: 2018年04月26日 14:15
 * C@author wyb
 **/
@Service("userService2")
public class UserServiceImpl2 implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void toInterface() {
		System.out.println("<<<<<<<< userService2-byName >>>>>>>>");
	}

	@Override
	public UserPO getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(UserPO user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public Integer update(UserPO user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public PageResult<UserPO> listData(UserParam param) {
		Page page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<UserPO> data = userMapper.queryPage(param);
		PageResult<UserPO> result = new PageResult<UserPO>();
		result.setTotal(page.getTotal());
		result.setPageNum(page.getPageNum());
		result.setRows(data);
		String uname = param.getUname();
		StringUtils.isNotBlank(uname);

		//uname.trim();
//		List names = Arrays.stream(uname.split(",")).filter( a -> StringUtils.isEmpty(a)).bmap(a -> {
//			return a;
//		}).collect(Collectors.toList());
		return result;
	}

	@Override
	public UserPO findUserByUderName(String username) {
		return null;
	}
}
