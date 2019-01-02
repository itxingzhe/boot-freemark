package cn.wyb.personal.common;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

/**
 * BaseControllerTest: controller层测试基类
 *
 * @author wangyibin
 * @date 2019/1/2 9:32
 * @see
 */
public class BaseControllerTest extends BaseTest {

    protected MockMvc                       mockMvc;

    protected MultiValueMap<String, String> params;

    protected MockHttpSession               session     = new MockHttpSession();

    protected MediaType                     contentType = MediaType.ALL;

    protected String                        url         = "/";

    @Autowired
    protected WebApplicationContext         wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter((Filter) wac.getBean("shiroFilter")).build();
        params = new LinkedMultiValueMap();
        params.add("t", System.currentTimeMillis() + "");
    }

    @Test
    public void getRequestTest() throws Exception {
        session = (MockHttpSession) getLoginSession();
        url = "/user/queryData";
        contentType = MediaType.APPLICATION_JSON_UTF8;
        MvcResult request = getGetRequestTest();
        System.out.println(JSON.toJSONString(request.getResponse()));
    }

    protected MvcResult getGetRequestTest() throws Exception {
        MvcResult result = mockMvc.perform(get(url).contentType(contentType).params(params).session(session)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andDo(print()).andReturn();
        return result;
    }

    @Test
    public void getRequestTestDemo() throws Exception {
        session = (MockHttpSession) getLoginSession();
        params = new LinkedMultiValueMap();
        params.add("t", System.currentTimeMillis() + "");
        // perform:执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
        MvcResult result = mockMvc.perform(
                // 声明发送一个get请求的方法。MockHttpServletRequestBuilder get(String urlTemplate,
                // Object... urlVariables)：
                // 根据uri模板和uri变量值得到一个GET请求方式的。 另外提供了其他的请求的方法，如：post、put、delete等。
                get("/user/queryData").
                // 数据参数的格式
                        contentType(MediaType.APPLICATION_FORM_URLENCODED).
                        // 添加request的参数，如发送请求的时候带上了key = value的参数。
                        params(params).
                        // 添加session
                        session((MockHttpSession) session))
                .
                // 添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）
                andExpect(status().isOk()).
                // 预期返回值的媒体类型
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                // 验证返回结果中的某个属性值
                andExpect(MockMvcResultMatchers.jsonPath("$.pageNum", is(0))).
                // 验证非空
                andExpect(jsonPath("$.total", notNullValue())).
                // 添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）
                andDo(print()).
                // 最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）；
                andReturn();
        System.out.println(JSON.toJSONString(result.getResponse()));
        System.out.println(JSON.toJSONString(result.getResponse().getContentAsString()));
    }

    protected HttpSession getLoginSession() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/user/doLogin").param("username", "zhangsan").param("password", "111111"))
                .andExpect(status().isOk()).andReturn();
        return result.getRequest().getSession();
    }

}
