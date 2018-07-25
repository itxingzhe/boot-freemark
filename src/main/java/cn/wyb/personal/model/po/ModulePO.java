package cn.wyb.personal.model.po;

import java.util.HashSet;
import java.util.Set;

public class ModulePO {
	private Integer mid;

	private String mname;

	private Set<RolePO> roles = new HashSet<>();

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname == null ? null : mname.trim();
	}

	public Set<RolePO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}
}