package cn.wyb.personal.model.param;

import java.io.Serializable;

public abstract class BmapApiBaseParam implements Serializable {

	private static final long serialVersionUID = 5192411734715139862L;

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
