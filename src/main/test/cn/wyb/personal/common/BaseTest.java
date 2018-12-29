package cn.wyb.personal.common;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.wyb.personal.BootFreemakerApplication;

/**
 * BaseTest: 单元测试基类
 *
 * @author wangyibin
 * @date 2018/12/29 10:27
 * @see
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootFreemakerApplication.class)
public class BaseTest {

    @Before
    public void init() {
        System.out.println(">>>>>>>>>>>>>>>>>开始测试<<<<<<<<<<<<<<<<<<<<");
    }

}
