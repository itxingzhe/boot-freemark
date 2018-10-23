package cn.wyb.personal.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wyb.personal.common.utils.BeanConvertor;
import cn.wyb.personal.dao.RoleMapper;
import cn.wyb.personal.model.po.RolePO;
import cn.wyb.personal.model.vo.role.RoleVO;
import cn.wyb.personal.service.user.RoleService;

/**
 * RoleServiceImpl: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/8/14 14:45
 * @see
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleDao;

	@Override
	public List<RoleVO> queryListData() {
		List<RolePO> list = roleDao.queryListData(new RolePO());
		List<RoleVO> vos = BeanConvertor.copyList(list, RoleVO.class);
		return vos;
	}
}
