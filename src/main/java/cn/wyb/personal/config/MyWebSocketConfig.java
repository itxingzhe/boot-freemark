package cn.wyb.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import cn.wyb.personal.handler.MyWebSocketHandler;
import cn.wyb.personal.interceptor.WebSocketInterceptor;

/**
 * WebSocketConfig: (请描述这个类).
 *
 * @author wangyibin
 * @date 2018/11/8 17:03
 * @see
 */

@Configuration
@EnableWebSocket
public class MyWebSocketConfig implements WebSocketConfigurer {

    /**
     * 提供配置自己的websocekt类即请求路径
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/webSocket/open").addInterceptors(new WebSocketInterceptor());
    }

    /**
     * 向spring容器注册javabean由spring容器来管理
     * 
     * @return
     */
    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }
}