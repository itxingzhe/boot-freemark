package cn.wyb.personal.service;

import cn.wyb.personal.model.vo.Authority.AuthorityVO;

import java.util.List;

/**
 * AuthorityService: (权限业务.
 *
 * @author wangyibin
 * @date 2018/8/14 15:21
 * @see
 */
public interface AuthorityService {

	List<AuthorityVO> queryData();

	void saveModuleAndRole(Long roleId, Long[] moduleIds);
}
