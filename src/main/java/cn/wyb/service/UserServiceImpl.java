package cn.wyb.service;

import cn.wyb.dao.UserPOMapper;
import cn.wyb.model.param.UserParam;
import cn.wyb.model.po.UserPO;
import cn.wyb.model.vo.PageResult;
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
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserPOMapper userPOMapper;

	@Override
	public UserPO getUser(Integer id) {
		return userPOMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer save(UserPO user) {
		return userPOMapper.insertSelective(user);
	}

	@Override
	public Integer update(UserPO user) {
		return userPOMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public PageResult<UserPO> listData(UserParam param) {
		Page page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
		List<UserPO> data = userPOMapper.queryPage(param);
		PageResult<UserPO> result = new PageResult<UserPO>();
		result.setTotal(page.getTotal());
		result.setPageNum(page.getPageNum());
		result.setData(data);
		String uname = param.getUname();
		StringUtils.isNotBlank(uname);
		//uname.trim();
//		List names = Arrays.stream(uname.split(",")).filter( a -> StringUtils.isEmpty(a)).map(a -> {
//			return a;
//		}).collect(Collectors.toList());
		return result;
	}
}
