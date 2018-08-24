package cn.wyb.personal.dao;

import cn.wyb.personal.model.param.ParamMap;
import cn.wyb.personal.model.po.ModuleRolePO;

import java.util.List;

public interface ModuleRoleMapper {
	int insert(ModuleRolePO record);

	int insertSelective(ModuleRolePO record);

	List<Integer> queryRoleModuleList(ParamMap paramMap);
}