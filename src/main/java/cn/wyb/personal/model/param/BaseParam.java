package cn.wyb.personal.model.param;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * BaseParam: 入参基础类.
 *
 * @author wangyibin
 * @date 2018/9/12 13:07
 * @see
 */
public class BaseParam implements Serializable {

	private static final long serialVersionUID = 3805995013111442856L;

	/**
	 * 排序字段名
	 */
	private String sort;

	/**
	 * 排序类型：asc 正序；desc 倒序
	 */
	private String order;

	/**
	 * 多排序
	 */
	private LinkedHashMap<String, SortType> sortMap;

	public enum SortType {

		ASC("asc"),

		DESC("desc");

		String code;

		SortType(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public static SortType getEnum(String code) {
			SortType[] values = SortType.values();
			for (SortType sort : values) {
				if (sort.code.equals(code)) {
					return sort;
				}
			}
			return null;
		}
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
		addSort(sort, order);
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
		addSort(sort, order);
	}

	public LinkedHashMap<String, SortType> getSortMap() {
		return sortMap;
	}

	public void setSortMap(LinkedHashMap<String, SortType> sortMap) {
		this.sortMap = sortMap;
	}

	public void addSort(String sort, SortType order) {
		if (sortMap == null) {
			sortMap = new LinkedHashMap<>();
		}
		sortMap.put(sort, order);
	}

	public void addSort(String sort, String order) {

		if (sort == null || order == null || sort.equals("") || order.equals("")) {
			return;
		}
		if (sortMap == null) {
			sortMap = new LinkedHashMap<>();
		}
		sortMap.put(sort, SortType.getEnum(order));
	}
}
