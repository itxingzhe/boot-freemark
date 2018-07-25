package cn.wyb.personal.model.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RolePO implements Serializable {
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
		this.rname = rname == null ? null : rname.trim();
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