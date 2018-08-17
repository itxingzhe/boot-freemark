package cn.wyb.personal.service.impl;

import cn.wyb.personal.common.utils.BeanConvertor;
import cn.wyb.personal.dao.ModuleMapper;
import cn.wyb.personal.model.po.ModulePO;
import cn.wyb.personal.model.vo.Authority.AuthorityVO;
import cn.wyb.personal.service.AuthorityService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * AuthorityServiceImpl: 权限业务类.
 *
 * @author wangyibin
 * @date 2018/8/14 15:22
 * @see
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<AuthorityVO> queryData() {
		List<ModulePO> pos = moduleMapper.queryData(new ModulePO());
		List<AuthorityVO> vos = BeanConvertor.copyList(pos, AuthorityVO.class);
		List<AuthorityVO> list = Lists.newArrayList();
		Iterator iterator = vos.iterator();
		while (iterator.hasNext()) {
			AuthorityVO vo = (AuthorityVO) iterator.next();
			if (vo.getParentId() == null) {
				list.add(vo);
				iterator.remove();
			}
		}
		List<AuthorityVO> parends = list;
		while (parends.size() > 0 && vos.size() > 0) {
			parends = setChildren(parends, vos);
		}
		return list;
	}


	public List<AuthorityVO> setChildren(List<AuthorityVO> parents, List<AuthorityVO> rosource) {
		ArrayList<AuthorityVO> newParents = Lists.newArrayList();
		for (AuthorityVO vo : parents) {
			Iterator<AuthorityVO> iterator = rosource.iterator();
			while (iterator.hasNext()) {
				AuthorityVO children = (AuthorityVO) iterator.next();
				if (vo.getMid().equals(children.getParentId())) {
					vo.getChildren().add(children);
					newParents.add(children);
					iterator.remove();
				}
			}
		}
		return newParents;
	}
}
