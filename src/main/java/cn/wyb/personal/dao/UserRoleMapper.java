package cn.wyb.personal.dao;

import cn.wyb.personal.model.po.UserRolePO;

public interface UserRoleMapper {
	int insert(UserRolePO record);

	int insertSelective(UserRolePO record);
}