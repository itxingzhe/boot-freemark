package cn.wyb.personal.service.impl;

import cn.wyb.personal.common.result.PageResult;
import cn.wyb.personal.dao.UserMapper;
import cn.wyb.personal.model.param.UserParam;
import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create Time: 2018年04月26日 14:15
 * C@author wyb
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void toInterface() {
		System.out.println("<<<<<<<< userService-byType >>>>>>>>");
	}

	@Override
	public UserPO getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(UserPO user) {
		md5Password(user);
		return userMapper.insertSelective(user);
	}

	private void md5Password(UserPO user) {
		if (user != null && StringUtils.isNotBlank(user.getPassword()) && StringUtils.isNotBlank(user.getUsername())) {
			ByteSource source = ByteSource.Util.bytes(user.getUsername());
			user.setPassword(new Md5Hash(user.getPassword(), source).toString());
		}
	}

	@Override
	public Integer update(UserPO user) {
		md5Password(user);
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
		return result;
	}

	@Override
	public UserPO findUserByUderName(String username) {
		List<UserPO> userList = userMapper.findByUserName(username);
		if (CollectionUtils.isNotEmpty(userList)) {
			return userList.get(0);
		}
		return null;
	}
}
