package com.loncark.langoapp.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail demoJobDetail() {
        return JobBuilder.newJob(DemoJob.class).withIdentity("DemoJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger demoJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();

        return TriggerBuilder.newTrigger().forJob(demoJobDetail())
                .withIdentity("DemoTrigger").withSchedule(scheduleBuilder).build();
    }
}

