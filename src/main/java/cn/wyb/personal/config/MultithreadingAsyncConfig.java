package cn.wyb.personal.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * MultithreadingScheduleTaskConfig : 多线程异步执行配置.
 *
 * @author : wangyibin
 * @date : 2019/4/28 9:36
 */
@Configuration
@EnableAsync
public class MultithreadingAsyncConfig {

  @Value("${schedule.core_pool_size}")
  private int corePoolSize;
  @Value("${schedule.max_pool_size}")
  private int maxPoolSize;
  @Value("${schedule.queue_capacity}")
  private int queueCapacity;

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.initialize();
    return executor;
  }

}
