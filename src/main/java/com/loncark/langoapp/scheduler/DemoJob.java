package com.loncark.langoapp.scheduler;

import com.loncark.langoapp.service.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Component
public class DemoJob extends QuartzJobBean  {
    private final UserService userService;

    @Autowired
    public DemoJob(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Users currently in the database: " + userService.findAll().stream().toList().size());
    }

}
