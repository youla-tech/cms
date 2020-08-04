package com.thinkcms.web.controller.job;

import com.thinkcms.core.annotation.Logs;
import com.thinkcms.core.api.BaseRedisService;
import com.thinkcms.core.enumerate.LogModule;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.core.utils.SecurityConstants;
import com.thinkcms.freemark.corelibs.job.JobExecute;
import com.thinkcms.freemark.enums.JobActionNotify;
import com.thinkcms.freemark.jobs.ContentClickAndGiveLikeJob;
import com.thinkcms.service.api.category.CmsCategoryService;
import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.service.api.fragment.FragmentService;
import com.thinkcms.service.dto.category.CmsCategoryDto;
import com.thinkcms.web.controller.BaseController;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("job")
public class JobController extends BaseController<FragmentService> {

    @Autowired
    JobExecute jobExecute;

    @Autowired
    CmsCategoryService cmsCategoryService;

    @Autowired
    ContentService contentService;

    @Autowired
    BaseRedisService baseRedisService;

    @Autowired
    ContentClickAndGiveLikeJob contentClickAndGiveLikeJob;


    @Logs(module = LogModule.JOB,operation = "生成首页")
    @GetMapping(value="homePageGen")
    public void reStaticFileByCid(){
        Map<String,Object> param = new HashMap<>();
        param.put(SecurityConstants.USER_ID,getUserId());
        jobExecute.execute(JobActionNotify.JOB_HOME_PAGE_RIGHT_NOW.setSecond(2),param);
    }


    @Logs(module = LogModule.JOB,operation = "全站生成")
    @GetMapping(value="reStaticWholeSite")
    public void reStaticWholeSite(){
        List<CmsCategoryDto> categoryDtos=cmsCategoryService.selectCategoryForWholeSiteGen();
        Map<String,Object> param = new HashMap<>();
        param.put(SecurityConstants.USER_ID,getUserId());
        param.put("categorys",categoryDtos);
        jobExecute.execute(JobActionNotify.JOB_WHOLE_SITE_RIGHT_NOW.setSecond(2),param);
    }


    @Logs(module = LogModule.JOB,operation = "清空缓存")
    @PutMapping(value="flushDB")
    public void flushDB(){
        try {
            contentClickAndGiveLikeJob.executeInternal(null);
        } catch (JobExecutionException e) {
            e.printStackTrace();
        }
        baseRedisService.flushDB();
    }



    @Logs(module = LogModule.JOB,operation = "重置索引库")
    @PutMapping(value="reContentToSolrCore")
    public void reContentToSolrCore(){
        Map<String,Object> param = new HashMap<>();
        param.put(SecurityConstants.USER_ID,getUserId());
        jobExecute.execute(JobActionNotify.JOB_SOLR_SYNC_DATA_RIGHT_NOW.setSecond(2).setMinute(0),param,true);
    }

    @Logs(module = LogModule.JOB,operation = "生成指定模板")
    @PutMapping(value="genFixedTemp")
    public void genFixedTemp(@RequestParam Map<String,Object> param){
        if(Checker.BeNull(param)||param.isEmpty()){
            throw new CustomException(ApiResult.result(20021));
        }
        param.put(SecurityConstants.USER_ID,getUserId());
        jobExecute.execute(JobActionNotify.JOB_FIXED_TEMPLATE_RIGHT_NOW.setSecond(2).setMinute(0),param,true);
    }

}
