package cn.wyb.personal.dao;

import cn.wyb.personal.model.po.ModuleRolePO;

public interface ModuleRoleMapper {
	int insert(ModuleRolePO record);

	int insertSelective(ModuleRolePO record);
}