package com.loncark.langoapp.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Scheduler {

    public void run() {
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory();
            org.quartz.Scheduler scheduler = factory.getScheduler();

            JobDetail jobDetail = JobBuilder
                    .newJob()
                    .ofType(DemoJob.class)
                    .withIdentity("DemoJob")
                    .build();

            SimpleTrigger simpleTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("DemoTrigger")
                    .forJob(jobDetail)
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(5)
                            .withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
                    .build();

            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
