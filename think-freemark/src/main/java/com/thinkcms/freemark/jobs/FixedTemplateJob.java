package com.thinkcms.freemark.jobs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.components.NotifyComponent;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成指定模板
 */
@Component
public class FixedTemplateJob extends QuartzJobBean {

    @Autowired
    NotifyComponent notifyComponent;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 业务触发执行
        JobDataMap params=jobExecutionContext.getTrigger().getJobDataMap();
        String uid = "";
        if(Checker.BeNotNull(params)&& !params.isEmpty()){
            Map<String,Object> mapData=new HashMap<>(16);
            Object userId=params.get(SecurityConstants.USER_ID);
            if(Checker.BeNotNull(userId) && userId instanceof  String){
                uid = userId.toString();
                params.remove(SecurityConstants.USER_ID);
            }
            for (Map.Entry<String, Object> m : params.entrySet()) {
                String value=m.getValue().toString();
                JSONObject jsonObject= JSON.parseObject(value);
                mapData.put(m.getKey(),jsonObject.get("html"));
            }
            notifyComponent.notifyFixedTemplate(uid,mapData);
        }

    }
}
