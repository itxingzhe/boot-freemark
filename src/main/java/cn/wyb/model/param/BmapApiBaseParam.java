package cn.wyb.model.param;

import java.io.Serializable;

public abstract class BmapApiBaseParam implements Serializable {

	private String output;
	private String ak;

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}
}
