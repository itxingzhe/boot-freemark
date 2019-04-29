package cn.wyb.personal.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
  public static boolean isAjaxRequest(HttpServletRequest request) {
    return (HEADER_X_REQUESTED_WITH_VALUE.equals(request.getHeader(HEADER_X_REQUESTED_WITH_KEY)));
  }

  public static String getLocalServerIp() {
    String ip = "";
    try {
      //获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
      InetAddress address = InetAddress.getLocalHost();
      ip = address.getHostAddress();
      //获取的是该网站的ip地址，比如我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地
      //InetAddress address1 = InetAddress.getByName("https://preview.testv2order.qqsk.com");
      //String hostAddress1 = address1.getHostAddress();
      //根据主机名返回其可能的所有InetAddress对象
      //InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
//      for (InetAddress addr : addresses) {
//        System.out.println(addr);
//      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    System.out.println("本机IP为：" + ip);
    return ip;
  }


  public static void main(String[] args) {
    getLocalServerIp();
  }


}
