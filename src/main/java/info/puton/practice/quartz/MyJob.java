package info.puton.practice.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;

/**
 * Created by taoyang on 2016/7/25.
 */
public class MyJob implements Job {

    Logger logger = Logger.getLogger(MyJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.info("lalala");

        System.out.println("i love puton.info");

        System.out.println("任务正在执行，执行时间: " + Calendar.getInstance().getTime());

        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("任务Key:" + jobName);


    }
}
