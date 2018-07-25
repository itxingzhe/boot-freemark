package cn.wyb.personal.service;

import cn.wyb.personal.common.result.PageResult;
import cn.wyb.personal.model.param.UserParam;
import cn.wyb.personal.model.po.UserPO;

/**
 * Create Time: 2018年04月26日 14:08
 * C@author wyb
 **/
public interface UserService {

	void toInterface();

	UserPO getUser(Integer id);

	Integer save(UserPO user);

	Integer update(UserPO user);

	PageResult<UserPO> listData(UserParam param);

	UserPO findUserByUderName(String username);
}
