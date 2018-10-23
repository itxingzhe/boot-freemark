package cn.wyb.personal.service.user;

import java.util.List;

import cn.wyb.personal.model.vo.Authority.AuthorityVO;

/**
 * AuthorityService: (权限业务.
 *
 * @author wangyibin
 * @date 2018/8/14 15:21
 * @see
 */
public interface AuthorityService {

	List<AuthorityVO> queryData();

    void saveModuleAndRole(Integer roleId, Integer[] moduleIds);
}
