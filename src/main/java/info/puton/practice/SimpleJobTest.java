package info.puton.practice;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class SimpleJobTest {

    public static void main(String[] args) throws SchedulerException {

        //任务在下一个整秒运行

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // and start it off
        scheduler.start();

        // define the job and tie it to our MyJob class
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        Date runTime = DateBuilder.evenSecondDate(new Date());

        // Trigger the job to run now, and then repeat every 2 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
//                .startNow()
                .startAt(runTime)
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

    }

}
