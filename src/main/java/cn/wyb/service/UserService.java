package cn.wyb.service;

import cn.wyb.model.param.UserParam;
import cn.wyb.model.po.UserPO;
import cn.wyb.model.vo.PageResult;

/**
 * Create Time: 2018年04月26日 14:08
 * C@author wyb
 **/
public interface UserService {

	UserPO getUser(Integer id);

	Integer save(UserPO user);

	Integer update(UserPO user);

	PageResult<UserPO> listData(UserParam param);
}
