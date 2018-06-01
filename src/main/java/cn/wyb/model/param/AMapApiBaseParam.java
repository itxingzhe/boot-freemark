package cn.wyb.model.param;

import java.io.Serializable;

public abstract class AMapApiBaseParam implements Serializable {

	private String output;
	private String scope;

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
