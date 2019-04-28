package cn.wyb.personal.task;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * MassScheduleTask : 集中的定时任务.
 *
 * @author : wangyibin
 * @date : 2019/4/28 15:24
 */
@Component
@Slf4j
public class MassScheduleTask {

  //项目启动后执行
  @Scheduled(cron = "0/5 * * * * *")
  public void scheduled() {
    log.info("定时任务>>>使用cron  {}", new Date());
  }

  //以固定速率执行 会阻塞 阻塞解除继续按顺序执行
  @Scheduled(fixedRate = 8000)
  public void scheduled1() {
    log.info("定时任务>>>使用fixedRate{}", new Date());
  }

  //以固定时间间隔执行
  @Scheduled(fixedDelay = 13000)
  public void scheduled2() {
    log.info("定时任务>>>fixedDelay{}", new Date());
  }
}
