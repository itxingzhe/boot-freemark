package cn.wyb.personal.model.param;

/**
 * Syslog: 日志参数.
 *
 * @author wangyibin
 * @date 2018/8/22 16:36
 * @see
 */
public class Syslog {

	private String loginId;

	private String loginName;

	private String ipAddress;

	private String methodName;

	private String methodRemark;

	private String optDate;

	private String operatingContent;

	private Integer optId;

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setMethodRemark(String methodRemark) {
		this.methodRemark = methodRemark;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getMethodRemark() {
		return methodRemark;
	}

	public String getOptDate() {
		return optDate;
	}

	public void setOperatingContent(String operatingContent) {
		this.operatingContent = operatingContent;
	}

	public String getOperatingContent() {
		return operatingContent;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public Integer getOptId() {
		return optId;
	}
}
