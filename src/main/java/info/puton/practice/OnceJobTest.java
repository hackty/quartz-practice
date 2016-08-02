package info.puton.practice;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class OnceJobTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job;
        Trigger trigger;
        Date runTime;

        //5秒后触发事件，只触发一次
        runTime = DateBuilder.nextGivenSecondDate(null, 5);
        job = JobBuilder.newJob(MyJob.class).withIdentity("job2", "group2").build();
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .startAt(runTime)
                .build();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        try {
            // 共执行20秒
            Thread.sleep(20L * 1000L);
        } catch (Exception e) {

        }
        scheduler.shutdown(true);
    }

}
