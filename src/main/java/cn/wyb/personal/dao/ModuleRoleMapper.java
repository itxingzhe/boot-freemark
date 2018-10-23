package cn.wyb.personal.dao;

import java.util.List;

import cn.wyb.personal.model.param.ParamMap;
import cn.wyb.personal.model.po.ModuleRolePO;

public interface ModuleRoleMapper {
	int insert(ModuleRolePO record);

	int insertSelective(ModuleRolePO record);

	List<Integer> queryRoleModuleList(ParamMap paramMap);

    int deleteByRidAndMid(ParamMap deleteParamMap);
}