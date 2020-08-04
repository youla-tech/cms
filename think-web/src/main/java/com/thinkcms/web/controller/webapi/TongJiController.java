package com.thinkcms.web.controller.webapi;

import com.thinkcms.service.api.content.ContentService;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.utils.HttpContextUtils;
import com.thinkcms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 内容 前端控制器
 * </p>
 *
 * @author LG
 * @since 2019-10-30
 */
@Validated
@RestController
@RequestMapping("/api/tongji")
public class TongJiController extends BaseController<ContentService> {

    @Autowired
    ThinkCmsConfig thinkCmsConfig;

    @GetMapping("goToBaiDuTj")
    public void goToBaiDuTj(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean mobile=HttpContextUtils.ckIsMobile();
        String url = mobile? thinkCmsConfig.getBaiDuTongjiUrlM(): thinkCmsConfig.getBaiDuTongJiUrl();
        response.sendRedirect(url);
    }

}
