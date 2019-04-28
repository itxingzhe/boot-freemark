package cn.wyb.personal.task;

import java.util.Date;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * CustomizedScheduleTask : 自定义定时任务.
 *
 * @author : wangyibin
 * @date : 2019/4/27 17:10
 */
@Component
@Configuration
@EnableScheduling
public class CustomizedScheduleTask implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

    taskRegistrar.addTriggerTask(
        //1.添加任务内容
        () -> System.out.println("这里执行任务......>> 自定义定时任务" + new Date().toString()),
        //2.设置执行周期(Trigger)
        triggerContext -> {
          //执行周期 30秒一次
          String cron = "0/30 * * * * ?";
          //返回执行周期(Date)
          return new CronTrigger(cron).nextExecutionTime(triggerContext);
        }
    );

  }
}
