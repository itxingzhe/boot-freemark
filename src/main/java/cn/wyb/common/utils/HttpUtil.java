package cn.wyb.common.utils;

import cn.wyb.model.vo.map.BmapPlaceAbroadResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Shine Xia
 * @since 09/20/2016
 */
public final class HttpUtil {


	private static final CloseableHttpClient httpclient = HttpClients.createDefault();

	/**
	 * 判断请求为AJAX请求
	 */
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String ACCEPT = "Accept";
	private static final String APPLICATION_JSON = "application/json";
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
	public static final String AMAP_AK = "ev82Imq86gQmRRZT5Chobk35KKPPh3NB";
	public static final String AMAP_SEARCH_URL = "http://api.map.baidu.com/place_abroad/v1/search";


	/**
	 * @param
	 * @return
	 * @getUserIpAddress : 获取用户的IP地址
	 * @author wangyibin
	 * @date 2018/6/6 13:44
	 **/
	public static String getUserIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				//根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/***
	 *
	 * sendGet:发送HttpGet请求.
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:36:30
	 * @param url 地址
	 * @param map 参数
	 * @return
	 */
	public static CloseableHttpResponse sendAmapGet(String url, Map<String, String> map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		StringEntity stringEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		String paramStr = toString(stringEntity);
		url += "?" + paramStr;
		CloseableHttpResponse response = null;
		try {
			String durl = URLDecoder.decode(url, "UTF-8");
			CloseableHttpClient client = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
					.setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
					.setConnectionRequestTimeout(5000)
					.build();
			HttpGet httpget = new HttpGet(url);
			httpget.setConfig(requestConfig);
			assembleHeader(httpget, ContentType.APPLICATION_FORM_URLENCODED);
			response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity resEntity = response.getEntity();

				String s = EntityUtils.toString(resEntity, "utf-8");
				JSONObject jsonObject = JSONObject.parseObject(s);
				JSONArray results = (JSONArray) jsonObject.get("results");
				List<BmapPlaceAbroadResultVO> list = results.toJavaList(BmapPlaceAbroadResultVO.class);
				System.out.println(list);
				System.out.println(s);
			} else {
				System.out.println("请求失败");
			}
			System.out.println(entity.toString());
		} catch (IOException e) {
			e.printStackTrace();
			//LOGGER.getBuilder().tag(BaseConsts.KIBANA_ACTION, "sendGet").error("httpget请求失败:", e);
		}
		return response;
	}

	/**
	 * 发送HttpGet请求
	 *
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		return sendGet(url, new HashMap<>());
	}

	/***
	 *
	 * sendGet:发送HttpGet请求.
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:36:30
	 * @param url 地址
	 * @param map 参数
	 * @return
	 */
	public static String sendGet(String url, Map<String, String> map) {
		String paramStr = toString(assembleParam(map, ContentType.APPLICATION_FORM_URLENCODED));
		if (url.indexOf("?") >= 0 && StringUtils.isNotBlank(paramStr)) {
			url += "&" + paramStr;
		} else if (url.indexOf("?") == -1 && StringUtils.isNotBlank(paramStr)) {
			url += "?" + paramStr;
		}

		CloseableHttpResponse response = null;
		try {
			//URLEncoder.encode(url, "UTF-8");
			HttpGet httpget = new HttpGet(url);
			//assembleHeader(httpget, ContentType.APPLICATION_FORM_URLENCODED);
			System.out.println(url);
			response = httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
			//LOGGER.getBuilder().tag(BaseConsts.KIBANA_ACTION, "sendGet").error("httpget请求失败:", e);
		}
		return getString(response);
	}

	/***
	 *
	 * sentPost:发送post请求， 注此方法适用于ContentType=application/x-www-form-urlencoded
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:37:04
	 * @param url
	 * @param map
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> map) {
		return sendPost(url, map, ContentType.APPLICATION_FORM_URLENCODED);
	}

	/***
	 *
	 * sentPost:发送无参post请求,注意，此方法默认ContentType=application/x-www-form-urlencoded
	 * ，如需发送json数据请使用其他重载方法
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:38:28
	 * @param url 地址
	 * @return
	 */
	public static String sendPost(String url) {
		return sendPost(url, new HashMap<>(), ContentType.APPLICATION_FORM_URLENCODED);
	}

	/***
	 *
	 * sentPost:发送无参post请求
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:38:28
	 * @param url 地址
	 * @param contentType 请求类型
	 * @return
	 */
	public static String sendPost(String url, ContentType contentType) {
		return sendPost(url, new HashMap<>(), contentType);
	}

	/****
	 *
	 * sendPost:发送post请求
	 *
	 * @author luochao
	 * @date 2018年1月29日 上午11:39:45
	 * @param url 地址
	 * @param map 参数
	 * @param contentType 请求类型
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> map, ContentType contentType) {
		HttpPost httppost = new HttpPost(url);
		assembleHeader(httppost, contentType);
		httppost.setEntity(assembleParam(map, contentType));

		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			//LOGGER.getBuilder().tag(BaseConsts.KIBANA_ACTION, "sendPost").error("httppost请求失败:", e);
		}
		return getString(response);
	}

	/***
	 *
	 * getJsonObjectResult:将返回值转换为jsonObject，兼容原来结果.
	 *
	 * @author luochao
	 * @date 2018年1月29日 下午4:01:17
	 * @param resultStr
	 * @return
	 */
	public static JSONObject getJsonObjectResult(String resultStr) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data", JSON.parseObject(resultStr));
		return jsonObject;
	}

	/***
	 *
	 * assembleParam:组装参数.
	 *
	 * @author luochao
	 * @date 2018年1月27日 下午12:59:54
	 * @param map 参数
	 * @param contentType 参数类型
	 * @return
	 */
	private static StringEntity assembleParam(Map<String, String> map, ContentType contentType) {
		StringEntity stringEntity = null;
		if (ContentType.APPLICATION_JSON.equals(contentType)) {
			stringEntity = new StringEntity(JSON.toJSONString(map), "UTF-8");
			stringEntity.setContentType("application/json");
		} else {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().trim()));
			}
			stringEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		}

		return stringEntity;
	}

	/**
	 * assembleHeader:组装请求头.
	 *
	 * @param request     请求头
	 * @param contentType 请求类型
	 * @author luochao
	 * @date 2018年1月27日 下午1:58:03
	 */
	private static void assembleHeader(HttpRequestBase request, ContentType contentType) {
		if (ContentType.APPLICATION_JSON.equals(contentType)) {
			request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
			request.addHeader(HttpHeaders.ACCEPT, "application/json");
		} else if (ContentType.APPLICATION_FORM_URLENCODED.equals(contentType)) {
			request.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
		} else {
			request.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
		}
	}

	/***
	 *
	 * getString:获取返回值，并关闭response.
	 *
	 * @author luochao
	 * @date 2018年1月27日 下午12:34:28
	 * @param response
	 * @return
	 */
	private static String getString(CloseableHttpResponse response) {
		if (response == null) {
			return null;
		}
		int statusCode = response.getStatusLine().getStatusCode();
		String resultStr = toString(response.getEntity());
		if (statusCode != HttpStatus.SC_OK) {
			//LOGGER.getBuilder().tag(BaseConsts.KIBANA_ACTION, "getString").error("http请求失败:" + resultStr);
			throw new RuntimeException("请求失败");
		}
		try {
			response.close();
		} catch (IOException e) {
			//LOGGER.getBuilder().error("关闭连接失败", e);
		}
		return resultStr;
	}

	/***
	 *
	 * toString:httpEntity转换为字符串.
	 *
	 * @author luochao
	 * @date 2018年1月27日 下午1:34:33
	 * @param httpEntity
	 * @return
	 */
	private static String toString(HttpEntity httpEntity) {
		String result = null;
		try {
			result = EntityUtils.toString(httpEntity, Consts.UTF_8);
		} catch (ParseException | IOException e) {
			//LOGGER.getBuilder().tag(BaseConsts.KIBANA_ACTION, "sendPost").error("httppost请求失败:", e);
		}

		return result;
	}

	/**
	 * 判断请求是否是ajax请求
	 *
	 * @param request
	 * @return
	 * @author panweiqiang
	 * @date 2017年8月3日 上午11:14:22
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader(X_REQUESTED_WITH);
		String accept = request.getHeader(ACCEPT);
		return XML_HTTP_REQUEST.equals(requestType) && accept.indexOf(APPLICATION_JSON) != -1;
	}

}
