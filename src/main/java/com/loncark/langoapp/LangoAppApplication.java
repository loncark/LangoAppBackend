package com.loncark.langoapp;

import com.loncark.langoapp.scheduler.Scheduler;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LangoAppApplication {

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(LangoAppApplication.class, args);

        Scheduler msc = new Scheduler();
        msc.run();
    }

}
