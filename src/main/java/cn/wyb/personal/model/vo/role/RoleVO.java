package cn.wyb.personal.model.vo.role;

import cn.wyb.personal.model.po.ModulePO;
import cn.wyb.personal.model.po.UserPO;

import java.util.HashSet;
import java.util.Set;

/**
 * RoleVO: 角色实体类.
 *
 * @author wangyibin
 * @date 2018/8/14 14:41
 * @see
 */
public class RoleVO {

	private Integer rid;

	private String rname;

	private Set<UserPO> roles = new HashSet<>();

	private Set<ModulePO> modules = new HashSet<>();

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Set<UserPO> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserPO> roles) {
		this.roles = roles;
	}

	public Set<ModulePO> getModules() {
		return modules;
	}

	public void setModules(Set<ModulePO> modules) {
		this.modules = modules;
	}
}
