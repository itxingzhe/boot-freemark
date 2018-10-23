package cn.wyb.personal.model.vo.Authority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import cn.wyb.personal.model.po.RolePO;

/**
 * Authority: 权限实体类.
 *
 * @author wangyibin
 * @date 2018/8/14 15:24
 * @see
 */
public class AuthorityVO {

	private Integer mid;

	private Integer parentId;

	private String mname;

	private String moduleType;

	private String isDefault;

	private String status;

	private String menuUrl;

	private Short showPosition;

	private List<AuthorityVO> children = Lists.newArrayList();

	private Set<RolePO> roles = new HashSet<>();

	public List<AuthorityVO> getChildren() {
		return children;
	}

	public void setChildren(List<AuthorityVO> children) {
		this.children = children;
	}

	public Integer getMid() {
		return mid;
	}

	public Integer getId() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMname() {
		return mname;
	}

	public String getText() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
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

	public Set<RolePO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}
}
