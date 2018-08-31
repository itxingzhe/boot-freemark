package cn.wyb.personal.model.po;

import java.util.Date;

public class OperationLogPO {
	private Long id;

	private Long loginId;

	private String loginName;

	private String ipAddress;

	private String methodName;

	private String methodRemark;

	private Date optDate;

	private String operatingContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark == null ? null : methodRemark.trim();
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getOperatingContent() {
		return operatingContent;
	}

	public void setOperatingContent(String operatingContent) {
		this.operatingContent = operatingContent == null ? null : operatingContent.trim();
	}
}