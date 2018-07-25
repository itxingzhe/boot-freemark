package cn.wyb.personal.dao;

import cn.wyb.personal.model.param.UserParam;
import cn.wyb.personal.model.po.UserPO;

import java.util.List;

public interface UserMapper {
	int deleteByPrimaryKey(Integer uid);

	int insert(UserPO record);

	int insertSelective(UserPO record);

	UserPO selectByPrimaryKey(Integer uid);

	int updateByPrimaryKeySelective(UserPO record);

	int updateByPrimaryKey(UserPO record);

	List<UserPO> queryPage(UserParam param);

	UserPO findByUserName(String username);
}