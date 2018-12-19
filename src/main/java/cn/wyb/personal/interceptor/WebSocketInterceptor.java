package cn.wyb.personal.interceptor;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import cn.wyb.personal.model.po.UserPO;

/**
 * WebSocketInterceptor: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/11/8 17:05
 * @see
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        // 此处可将用户信息放入WebSocketSession的属性当中，以便定向发送消息。
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal != null) {
            attributes.put("WEBSOCKET_USERID", ((UserPO) principal).getUid().longValue());
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
