package cn.wyb.personal.dao;

import cn.wyb.personal.model.po.ModulePO;

import java.util.List;

public interface ModuleMapper {
	int deleteByPrimaryKey(Integer mid);

	int insert(ModulePO record);

	int insertSelective(ModulePO record);

	ModulePO selectByPrimaryKey(Integer mid);

	int updateByPrimaryKeySelective(ModulePO record);

	int updateByPrimaryKey(ModulePO record);

	List<ModulePO> queryData(ModulePO modulePO);
}