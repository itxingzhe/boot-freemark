package cn.wyb.personal.common.utils;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * CommonUtils : 公用工具类.
 *
 * @author : wangyibin
 * @date : 2019/4/29 17:02
 */
public class CommonUtils {


  /**
   * 多元素拼装.
   *
   * @param resource :
   * @return java.util.List
   * @create by : wangyibin
   * @createTime : 2019/4/29 17:02
   */
  public static List multigroupStringCombination(List<List> resource) {
    if (null == resource) {
      return null;
    } else if (resource.size() <= 1) {
      return resource.get(0);
    } else {
      List<Object> list1 = resource.get(0);
      List<Object> list2 = resource.get(1);
      if (list1 == null || list1.size() == 0) {
        resource.remove(0);
      } else if (list2 == null || list2.size() == 0) {
        resource.remove(1);
      } else {
        int size1 = list1.size();
        int size2 = list2.size();
        List<Object> newList1 = Lists.newArrayListWithExpectedSize(size1 * size2);
        for (Object s1 : list1) {
          for (Object s2 : list2) {
            if (null == s1 || null == s2) {
              continue;
            }
            newList1.add(s1 + "," + s2);
          }
        }
        resource.set(0, newList1);
        resource.remove(1);
      }
      return multigroupStringCombination(resource);
    }
  }

}
