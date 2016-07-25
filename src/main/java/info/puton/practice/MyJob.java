package info.puton.practice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;

/**
 * Created by taoyang on 2016/7/25.
 */
public class MyJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("i love puton.info");
        System.out.println("任务正在执行，执行时间: " + Calendar.getInstance().getTime());
    }
}
