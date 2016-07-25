package info.puton.practice;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by taoyang on 2016/7/25.
 */
public class RunMyJob {

    public static void main(String[] args) throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = null;

        scheduler = StdSchedulerFactory.getDefaultScheduler();
        // and start it off
        scheduler.start();

        // define the job and tie it to our MyJob class
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        Date runTime = DateBuilder.evenMinuteDate(new Date());

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
//                .startNow()
                .startAt(runTime)
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

    }

}
