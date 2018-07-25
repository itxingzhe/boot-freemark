package cn.wyb.personal.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Bean转化类
 *
 * @author: panweiqiang
 * @since: 2016年7月7日 上午10:38:44
 * @history:
 */
public class BeanConvertor {

	//private static final LogHelper LOGGER = LogHelper.getLogger(BeanConvertor.class);

	/**
	 * 功能说明：将bean转化成另一种Bean的实体
	 *
	 * @param object
	 * @param entityClass
	 * @return T
	 */
	public static <T> T convertBean(Object object, Class<T> entityClass) {
		if (object == null) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(object), entityClass);

	}

	/**
	 * 对象转换
	 *
	 * @param source           原对象
	 * @param target           目标对象
	 * @param ignoreProperties 排除要copy的属性
	 * @return
	 * @create 2016年7月18日 上午11:14:35 luochao
	 * @history
	 */
	public static <T> T copy(Object source, Class<T> target, String... ignoreProperties) {
		if (source == null) {
			return null;
		}
		T targetInstance = null;
		try {
			targetInstance = target.newInstance();
		} catch (Exception e) {
			//LOGGER.getBuilder().error("", e);
		}
		if (ignoreProperties == null || ignoreProperties.length <= 0) {
			BeanUtils.copyProperties(source, targetInstance);
		} else {
			BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
		}
		return targetInstance;
	}

	/**
	 * 对象转换（list）
	 *
	 * @param list             原数据
	 * @param target           目标对象
	 * @param ignoreProperties 排除要copy的属性
	 * @return
	 * @create 2016年7月18日 上午11:15:42 luochao
	 * @history
	 */
	public static <T, E> List<T> copyList(List<E> list, Class<T> target, String... ignoreProperties) {
		List<T> targetList = new ArrayList<T>();
		if (CollectionUtils.isEmpty(list)) {
			return targetList;
		}
		for (E e : list) {
			targetList.add(copy(e, target, ignoreProperties));
		}
		return targetList;
	}

	/**
	 * map转对象
	 *
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @history
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> target) {
		T targetInstance = null;
		try {
			targetInstance = target.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(targetInstance, map);
		} catch (Exception e) {
			//LOGGER.getBuilder().error("", e);
		}
		return targetInstance;
	}

	/**
	 * 对象转map
	 *
	 * @param obj
	 * @return
	 * @history
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		return convertBean(obj, Map.class);
	}

	/**
	 * 对象转map
	 *
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author luochao
	 * @date 2017年8月10日 上午9:38:30
	 */
	public static Map<String, Object> objectToMap2(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	/**
	 * 功能说明：拷贝第一个参数的非空值到第二个参数
	 *
	 * @param source
	 * @param target
	 */
	public static void copyNonNull(Object source, Object target) {
		if (source == null | target == null) {
			return;
		}
		String[] ignoreProperties = getNullPropertyNames(source);
		BeanUtils.copyProperties(source, target, ignoreProperties);
	}

	/**
	 * 功能说明：拷贝第一个参数的非空值到第二个参数
	 *
	 * @param source
	 * @param target
	 */
	public static void copyNonNull(Map<String, Object> source, Object target) {
		if (source == null | target == null) {
			return;
		}
		Object sourceObject = mapToObject(source, target.getClass());
		String[] ignoreProperties = getNullPropertyNames(sourceObject);
		BeanUtils.copyProperties(sourceObject, target, ignoreProperties);
	}

	/**
	 * 功能说明：获取为空的属性
	 *
	 * @param source
	 * @return String[]
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}

		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
