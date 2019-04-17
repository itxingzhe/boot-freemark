package cn.wyb.personal.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ServletUtils : servlet工具类.
 *
 * @author : wangyibin
 * @date : 2019/4/17 14:01
 */
public class ServletUtils {

  public static final String HEADER_X_REQUESTED_WITH_KEY = "X-Requested-With";
  public static final String HEADER_X_REQUESTED_WITH_VALUE = "XMLHttpRequest";

  /**
   * 判断请求是否是ajax请求.
   *
   * @param request :
   * @return boolean
   * @create by : wangyibin
   * @createTime : 2019/4/17 14:02
   */
  private boolean isAjaxRequest(HttpServletRequest request) {
    return (HEADER_X_REQUESTED_WITH_VALUE.equals(request.getHeader(HEADER_X_REQUESTED_WITH_KEY)));
  }


}
