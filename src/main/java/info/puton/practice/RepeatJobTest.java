package info.puton.practice;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class RepeatJobTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job;
        Trigger trigger;
        Date runTime;

        //任务5秒钟后执行，并且开始执行之后，每隔1秒钟再执行一次，重复3次（算上第1次一共4次）
        runTime = DateBuilder.nextGivenSecondDate(null, 5);
        job = JobBuilder.newJob(MyJob.class).withIdentity("job3", "group3").build();
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group3")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(3)
                        .withIntervalInSeconds(1))
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
