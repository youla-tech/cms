package com.thinkcms.web.config;

import com.thinkcms.core.utils.JobUtil;
import com.thinkcms.freemark.jobs.HardWareMonitorJob;
import com.thinkcms.freemark.jobs.LegalVerifyJob;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HardWareMonitorConfig implements CommandLineRunner {

    @Autowired
    Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        JobUtil.createJobByCron(scheduler,"HardWareMonitor","group5","0/3 * * * * ?", HardWareMonitorJob.class);
        JobUtil.createJobByCron(scheduler,"LegalVerifyJob1","group5","0 15 10 15 * ?", LegalVerifyJob.class);
    }
}
