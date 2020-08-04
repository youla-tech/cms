package com.thinkcms.freemark.jobs;

import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.components.NotifyComponent;
import com.thinkcms.freemark.observers.ContentObserver;
import com.thinkcms.service.dto.content.ContentDto;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时生成批量文章
 */
@Component
public class ContentListGenJob extends QuartzJobBean {

    @Autowired
    NotifyComponent notifyComponent;

    @Autowired
    ContentObserver contentObserver;


    private List<ContentDto> contentDtos =new ArrayList<>();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 业务触发执行
        JobDataMap params=jobExecutionContext.getTrigger().getJobDataMap();
        String uid = "";
        if(Checker.BeNotNull(params)&& !params.isEmpty()){
            Object contentObjs=params.get("contents");
            Object userId=params.get(SecurityConstants.USER_ID);
            if(Checker.BeNotNull(contentObjs) && contentObjs instanceof  List){
                List<ContentDto> contents= ( List<ContentDto>)contentObjs;
                if(Checker.BeNotEmpty(contents)){
                    this.contentDtos = contents;
                }
            }
            if(Checker.BeNotNull(userId) && userId instanceof  String){
                uid = userId.toString();
            }
        }
        notifyComponent.notifyCreates(contentObserver,contentDtos, uid);
    }
}
