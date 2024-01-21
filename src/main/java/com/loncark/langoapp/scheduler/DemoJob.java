package com.loncark.langoapp.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DemoJob implements Job {
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(
                "Hello from the Job !"
                        + context.getRefireCount());
    }
}
