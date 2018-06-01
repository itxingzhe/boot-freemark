package cn.wyb.common.utils;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class HttpClientUtil {

	public static URI buildURI(String target, Map<String, String> queries) throws IOException, URISyntaxException {
		List<NameValuePair> nameValuePairs = queries.entrySet().stream()
				.map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
		return new URIBuilder(target)
				.setParameters(nameValuePairs)
				.build();
	}

	public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int maxRoute, String hostname, int port) {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
				.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
				.getSocketFactory();
		Registry<ConnectionSocketFactory> registry = RegistryBuilder
				.<ConnectionSocketFactory>create().register("http", plainsf)
				.register("https", sslsf).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(
				registry);
		// 将最大连接数增加
		cm.setMaxTotal(maxTotal);
		// 将每个路由基础的连接增加
		cm.setDefaultMaxPerRoute(maxPerRoute);
		HttpHost httpHost = new HttpHost(hostname, port);
		// 将目标主机的最大连接数增加
		cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);
		cm.setConnectionConfig(httpHost, ConnectionConfig.DEFAULT);
		// 请求重试处理
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception,
										int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

//                HttpClientContext clientContext = HttpClientContext
//                        .adapt(context);
//                HttpRequest request = clientContext.getRequest();
//                // 如果请求是幂等的，就再次尝试
//                if (!(request instanceof HttpEntityEnclosingRequest)) {
//                    return true;
//                }
				return false;
			}
		};
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm)
				.setRetryHandler(httpRequestRetryHandler)
				.build();
		return httpClient;
	}
}
