package cn.wyb.personal.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ServletUtils : servlet工具类.
 *
 * @author : wangyibin
 * @date : 2019/4/17 14:01
 */
public class ServletUtils {

  /**
   * 判断请求是否是ajax请求.
   *
   * @param request :
   * @return boolean
   * @create by : wangyibin
   * @createTime : 2019/4/17 14:02
   */
  private boolean isAjax(HttpServletRequest request) {
    return (request.getHeader("X-Requested-With") != null
        && "XMLHttpRequest"
        .equals(request.getHeader("X-Requested-With").toString()));
  }


}
