package com.thinkcms.freemark.jobs;

import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.components.NotifyComponent;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 全站生成
 */
public class WholeSiteGenJob extends QuartzJobBean {

    @Autowired
    NotifyComponent notifyComponent;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap params=jobExecutionContext.getTrigger().getJobDataMap();
        if(Checker.BeNotNull(params)&& !params.isEmpty()){
            Object userId=params.get(SecurityConstants.USER_ID);
            Object categorys=params.get("categorys");
            if(Checker.BeNotNull(userId)&&Checker.BeNotNull(categorys)){
                List<CmsCategoryDto> category = (List<CmsCategoryDto>) categorys;
                notifyComponent.notifyWholeSite(category,userId.toString());
            }
        }
    }
}
