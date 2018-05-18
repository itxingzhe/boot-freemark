package cn.wyb.dao;

import cn.wyb.model.param.UserParam;
import cn.wyb.model.po.UserPO;

import java.util.List;

public interface UserPOMapper {

	int deleteByPrimaryKey(Integer uid);

	int insert(UserPO record);

	int insertSelective(UserPO record);

	UserPO selectByPrimaryKey(Integer uid);

	int updateByPrimaryKeySelective(UserPO record);

	int updateByPrimaryKey(UserPO record);

	List<UserPO> queryPage(UserParam param);
}