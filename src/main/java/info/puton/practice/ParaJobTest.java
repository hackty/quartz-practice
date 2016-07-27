package info.puton.practice;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class ParaJobTest {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        Date runTime = DateBuilder.nextGivenSecondDate(null, 10);
        JobDetail job = JobBuilder.newJob(MyParaJob.class).withIdentity("job1", "group1").build();
        job.getJobDataMap().put(MyParaJob.JOB_DATA_MAP_KEY, 10);
        // 每隔2秒执行，重复5次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(5))
                .startAt(runTime).build();
        scheduler.scheduleJob(job, trigger);

        scheduler.start();
        try {
            Thread.sleep(35L * 1000L);
        } catch (Exception e) {
        }
        scheduler.shutdown(true);
    }

}