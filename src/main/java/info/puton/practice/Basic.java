package info.puton.practice;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by taoyang on 2016/7/25.
 */
public class Basic {

    public static void main(String[] args) throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = null;

        scheduler = StdSchedulerFactory.getDefaultScheduler();
        // and start it off
        scheduler.start();


    }

}
