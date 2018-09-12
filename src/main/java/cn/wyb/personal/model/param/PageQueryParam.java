package cn.wyb.personal.model.param;

import java.io.Serializable;

/**
 * PageQueryParam: 分页查询基础类.
 *
 * @author wangyibin
 * @date 2018/9/11 16:45
 * @see
 */
public class PageQueryParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = -5129876433607104718L;

	/**
	 * 分页起始索引
	 */
	private int offset;

	/**
	 * 分页每页条数
	 */
	private int limit;

	public Integer getOffset() {
		if (offset > 0) {
			return offset;
		}
		return 0;
	}

	public void setOffset(int offset) {
		if (offset >= 0) {
			this.offset = offset;
		}
	}

	public Integer getLimit() {
		if (limit >= 0) {
			return limit;
		}
		return 10;
	}

	public void setLimit(int limit) {
		if (limit >= 0) {
			this.limit = limit;
		}
	}
}
