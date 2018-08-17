package cn.wyb.personal.dao;

import cn.wyb.personal.model.po.RolePO;

import java.util.List;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer rid);

	int insert(RolePO record);

	int insertSelective(RolePO record);

	RolePO selectByPrimaryKey(Integer rid);

	int updateByPrimaryKeySelective(RolePO record);

	int updateByPrimaryKey(RolePO record);

	List<RolePO> queryListData(RolePO po);
}