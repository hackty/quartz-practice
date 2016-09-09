package info.puton.practice.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class NowJobTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job;
        Trigger trigger;

        //立即触发事件，只触发一次
        job = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group2").build();
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        try {
            // 共执行5秒
            Thread.sleep(5L * 1000L);

        } catch (Exception e) {

        }
        scheduler.scheduleJob(job, trigger);
//        scheduler.shutdown(true);
    }

}
