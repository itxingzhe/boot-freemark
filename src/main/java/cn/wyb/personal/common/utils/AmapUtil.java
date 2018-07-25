package cn.wyb.personal.common.utils;

import cn.wyb.personal.model.param.AmapCoordinateParam;
import cn.wyb.personal.model.vo.map.PointStrVO;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class AmapUtil {

	private static final String AMAP_AK = "cfc30045c644fc76d6244654e3622394";
	private static final String AMAP_ROOT_URL = "https://restapi.amap.com";
	private static final String AMAP_GEOCONV_PORT = "/v3/assistant/coordinate";
	private static final String POINT_CONVERT = "/convert";

	public static Map<String, String> Param2Map(Object param) {
		HashMap<String, String> map = Maps.newHashMap();
		String output = null;
		if (param != null) {
			Class clazz = param.getClass();
			List<Field> fieldList = new ArrayList<>();
			while (clazz != Object.class) {
				fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
				clazz = clazz.getSuperclass();
			}
			for (Field field : fieldList) {
				field.setAccessible(true);
				String name = field.getName();
				if (StringUtils.isNotBlank(name)) {
					try {
						Object o = field.get(param);
						if (o != null && StringUtils.isNotBlank(o.toString())) {
							if ("output".equals(name)) {
								output = o.toString();
							}
							map.put(name, o.toString());
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (StringUtils.isBlank(output)) {
			output = "json";
		}
		output = "xml".equals(output.toLowerCase()) ? "xml" : "json";
		map.put("output", output);
		map.put("key", AMAP_AK);
		return map;
	}

	public static List<PointStrVO> getCoordinateResponse(AmapCoordinateParam param) {
		String url = AMAP_ROOT_URL + AMAP_GEOCONV_PORT + POINT_CONVERT;
		Map<String, String> map = Param2Map(param);
		String s = HttpUtil.sendGet(url, map);
		//System.out.println(s);
		if (StringUtils.isBlank(s)) {
			return null;
		}
		JSONObject jsonObject = JSONObject.parseObject(s);
		ArrayList<PointStrVO> result = new ArrayList<>();
		if (jsonObject.getInteger("status") == 1) {
			String locations = jsonObject.getString("locations");
			if (locations.contains(";")) {
				String[] split = locations.split(";");
				for (String locationStr : split) {
					String[] location = locationStr.split(",");
					result.add(new PointStrVO(location[0], location[1]));
				}
			} else {
				String[] location = locations.split(",");
				result.add(new PointStrVO(location[0], location[1]));
			}
		}
		return result;
	}


}
