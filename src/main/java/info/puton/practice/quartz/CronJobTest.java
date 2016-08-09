package info.puton.practice.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by taoyang on 2016/7/25.
 */
public class CronJobTest {

    public static void main(String[] args) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail job;
        Trigger trigger;

        job = JobBuilder.newJob(MyJob.class).withIdentity("job4", "group4").build();
        // @NOTICE
        // 与SimpleTrigger对比：类不同了，现在的是Trigger的子类CronTrigger；withSchedule中的参数变为CronScheduleBuilder了
        // CronScheduleBuilder可以通过类似"0/3 * * * * ?"这种表达式来创建定时任务
        // 当前这个表达式的定义是每个秒是3秒倍数时，或者是0秒的时候，都触发任务
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger4", "group4")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();
//        try {
//            // 等待60秒查看效果
//            Thread.sleep(60L * 1000L);
//        } catch (Exception e) {
//        }
//        scheduler.shutdown(true);
    }

    /*
--------------------------------------
    0 0 12 * * ?            每天12点触发
    0 15 10 ? * *           每天10点15分触发
    0 15 10 * * ?           每天10点15分触发
    0 15 10 * * ? *         每天10点15分触发
    0 15 10 * * ? 2005      2005年每天10点15分触发
    0 * 14 * * ?            每天下午的 2点到2点59分每分触发
    0 0/5 14 * * ?          每天下午的 2点到2点59分(整点开始，每隔5分触发)
    0 0/5 14,18 * * ?       每天下午的 2点到2点59分(整点开始，每隔5分触发) 每天下午的 18点到18点59分(整点开始，每隔5分触发)
    0 0-5 14 * * ?          每天下午的 2点到2点05分每分触发
    0 10,44 14 ? 3 WED      3月分每周三下午的 2点10分和2点44分触发
    0 15 10 ? * MON-FRI     从周一到周五每天上午的10点15分触发
    0 15 10 15 * ?          每月15号上午10点15分触发
    0 15 10 L * ?           每月最后一天的10点15分触发
    0 15 10 ? * 6L          每月最后一周的星期五的10点15分触发
    0 15 10 ? * 6L 2002-2005    从2002年到2005年每月最后一周的星期五的10点15分触发
    0 15 10 ? * 6#3         每月的第三周的星期五开始触发
    0 0 12 1/5 * ?          每月的第一个中午开始每隔5天触发一次
    0 11 11 11 11 ?         每年的11月11号 11点11分触发(光棍节)
--------------------------------------
 */

}
