package cn.wyb.personal.service;

import org.springframework.scheduling.annotation.Async;

/**
 * AsyncDemoService : 多线程异步执行.
 *
 * @author : wangyibin
 * @date : 2019/4/28 9:42
 */
public class AsyncDemoService {

  @Async
  public void taskDemo() {
    System.out.println("多线程异步执行任务执行了......");
  }

}
