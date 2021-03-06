package cn.wyb.personal.model.po;

import java.util.HashSet;
import java.util.Set;

public class ModulePO {

	private Integer mid;

	private Integer parentId;

	private String mname;

	private String moduleType;

	private String isDefault;

	private String status;

	private String menuUrl;

	private Short showPosition;

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Short getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(Short showPosition) {
		this.showPosition = showPosition;
	}
}