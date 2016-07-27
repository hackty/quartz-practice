package info.puton.practice;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by taoyang on 2016/7/25.
 */

//这两行注解。。。 极度坑爹，也是和2.0之前版本所不同的地方，务必注意 @NOTICE
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyParaJob implements Job {

    private int myCount = 0;
    private static int myStaticCount = 0;

    public static final String JOB_DATA_MAP_KEY = "key1";

    public MyParaJob() {
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        int jobDataMapInt = data.getInt(JOB_DATA_MAP_KEY);
        // 这里注释的内容用于理解DisallowConcurrentExecution这个注解
        //if (jobDataMapInt == 11) {
        //  try {
        //      Thread.sleep(11000);
        //  } catch (InterruptedException e) {
        //      e.printStackTrace();
        //  }
        //}
        System.out.println("任务Key:" + jobName + " 正在执行，执行时间: " + dateFormat.format(Calendar.getInstance().getTime()));
        System.out.println("***private成员变量为:" + myCount + ",static成员变量为:" + myStaticCount + ",JobDataMap保存的变量为:" + jobDataMapInt);
        myCount++;
        jobDataMapInt++;
        data.put(JOB_DATA_MAP_KEY, jobDataMapInt);
        myStaticCount++;

    }
}
