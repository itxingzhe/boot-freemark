package cn.wyb.personal.service.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.wyb.personal.common.utils.BeanConvertor;
import cn.wyb.personal.dao.ModuleMapper;
import cn.wyb.personal.dao.ModuleRoleMapper;
import cn.wyb.personal.model.param.ParamMap;
import cn.wyb.personal.model.po.ModulePO;
import cn.wyb.personal.model.po.ModuleRolePO;
import cn.wyb.personal.model.vo.Authority.AuthorityVO;
import cn.wyb.personal.service.user.AuthorityService;

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

	@Autowired
	private ModuleRoleMapper moduleRoleMapper;

	@Override
	public List<AuthorityVO> queryData() {
		List<ModulePO> pos = moduleMapper.queryData(new ModulePO());
		List<AuthorityVO> vos = BeanConvertor.copyList(pos, AuthorityVO.class);
        // 获取父级权限
		List<AuthorityVO> list = Lists.newArrayList();
		Iterator iterator = vos.iterator();
		while (iterator.hasNext()) {
			AuthorityVO vo = (AuthorityVO) iterator.next();
			if (vo.getParentId() == null) {
				list.add(vo);
				iterator.remove();
			}
		}
        // 封装父级的子级权限
		List<AuthorityVO> parends = list;
		while (parends.size() > 0 && vos.size() > 0) {
			parends = setChildren(parends, vos);
		}
		return list;
	}

	@Override
    public void saveModuleAndRole(Integer roleId, Integer[] moduleIds) {
		if (ArrayUtils.isNotEmpty(moduleIds) && roleId != null) {
			ParamMap paramMap = new ParamMap();
			paramMap.put("rid", roleId);
			List<Integer> moduleList = moduleRoleMapper.queryRoleModuleList(paramMap);
            ArrayList<Integer> list = Lists.newArrayList(moduleIds);
            boolean equalCollection = CollectionUtils.isEqualCollection(list, moduleList);
            if (equalCollection) {
                return;
            }
			//交集
			Collection intersection = CollectionUtils.intersection(list, moduleList);
			//交集的补集
            // Collection disjunction = CollectionUtils.disjunction(list, moduleList);
			//差集
            Collection<Integer> insert = CollectionUtils.subtract(list, intersection);
            Collection<Integer> delete = CollectionUtils.subtract(moduleList, intersection);
            for (Integer moduleId : insert) {
				ModuleRolePO po = new ModuleRolePO();
				po.setRid(roleId.intValue());
				po.setMid(moduleId.intValue());
                int row = moduleRoleMapper.insert(po);
                System.out.println("id" + row);
			}
            ParamMap deleteParamMap = new ParamMap();
            deleteParamMap.put("rid", roleId);
            deleteParamMap.put("deleteMidList", delete);
            int rowNum = moduleRoleMapper.deleteByRidAndMid(deleteParamMap);
            System.out.println("delete:" + rowNum);
		}

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
