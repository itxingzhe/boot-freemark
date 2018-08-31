package cn.wyb.personal.dao;

import cn.wyb.personal.model.po.OperationLogPO;

public interface OperationLogMapper {
	int insert(OperationLogPO record);

	int insertSelective(OperationLogPO record);
}