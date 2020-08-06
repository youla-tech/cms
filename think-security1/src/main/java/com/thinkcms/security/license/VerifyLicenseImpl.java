package com.thinkcms.security.license;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.thinkcms.core.api.BaseRedisService;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.model.LicenseProperties;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VerifyLicenseImpl implements VerifyLicense {
    private static final Logger log = LoggerFactory.getLogger(VerifyLicenseImpl.class);

    private ThinkCmsConfig cmsConfig;

    public BaseRedisService baseRedisService;


    public VerifyLicenseImpl() {
    }


    public VerifyLicenseImpl(ThinkCmsConfig cmsConfig, BaseRedisService baseRedisService) {
        this.cmsConfig = cmsConfig;
        this.baseRedisService = baseRedisService;
    }


    public Boolean verify(String licensePath, Map<String, Object> propertie) throws Exception {
        if (!propertie.isEmpty()) {
            LicenseProperties properties = (LicenseProperties) JSON.parseObject(JSON.toJSONString(propertie), LicenseProperties.class);
            if (!propertie.containsKey("signaturer") || Checker.BeBlank(propertie.get("signaturer").toString()).booleanValue()) {
                log.error("20028:license证书不合法");
                throw new CustomException(ApiResult.result(20028));
            }
            String sign = propertie.get("signaturer").toString();
            propertie.remove("signaturer");
            try {
                boolean valid = VerifyLicenseUtil.verify(propertie.toString().getBytes(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCagWXCpLlk/X3Alvr6mDoYyXtIcnd5Bbe9Tvan6+dy5MkDJ5urNVX71Fp3bObKjkDod+fth4cOOu+wEtD8MI5ycnDQZDDB5YylKhl68q6eZnMOZ20u/eG3TfaNmQwjcuSZeCxhBF99qnA+Vn67xYTHqPCBVIxbcRtghIp9EVvV6wIDAQAB", sign);
                if (!valid) {
                    log.error("20028:license证书不合法");
                    throw new CustomException(ApiResult.result(20028));
                }
            } catch (Exception e) {
                throw new CustomException(ApiResult.result(20028));
            }
            propertie.put("signaturer", sign);
            verifyDate(properties);
            verifyDomain(properties);
        }
        return Boolean.valueOf(true);
    }


    private void verifyDate(LicenseProperties properties) {
        DateTime dateTime2, dateTime3, dateTime1 = DateUtil.date();
        Date start = null, end = null;
        try {
            String[] startEnd = properties.getStartStopTime().split("~");
            dateTime2 = DateUtil.parse(startEnd[0]);
            dateTime3 = DateUtil.parse(startEnd[1]);
        } catch (Exception e) {
            log.error("20029:license证书日期不合法");
            throw new CustomException(ApiResult.result(20029));
        }
        int isSatrt = DateUtil.compare((Date) dateTime1, (Date) dateTime2);
        int isEnd = DateUtil.compare((Date) dateTime1, (Date) dateTime3);
        if (isSatrt == -1) {
            log.error("20034:license证书授权未生效");
            throw new CustomException(ApiResult.result(20034));
        }
        if (isEnd == 1) {
            log.error("20030:license证书授权已过期");
            throw new CustomException(ApiResult.result(20030));
        }
    }

    private void verifyDomain(LicenseProperties properties) {
        if (!"*".equals(properties.getDomain())) {
            String[] domins = properties.getDomain().split("\\|");
            if (domins.length > 0) {
                List<String> domainList = Arrays.asList(domins);
                String siteDomain = this.cmsConfig.getSiteDomain();
                String serverApi = this.cmsConfig.getServerApi();
                if (Checker.BeNotBlank(siteDomain).booleanValue()) {
                    siteDomain = siteDomain.replace("http://", "").trim();
                    boolean siteDomainIgnore = ignore(siteDomain);
                    if (!siteDomainIgnore && !domainList.contains(siteDomain)) {
                        log.error("20032:license授权域名不合法");
                        throw new CustomException(ApiResult.result(20032));
                    }
                }
        
                if (Checker.BeNotBlank(serverApi).booleanValue()) {
                    serverApi = serverApi.replace("http://", "").trim();
                    boolean serverApiIgnore = ignore(serverApi);
                    if (!serverApiIgnore && !domainList.contains(serverApi)) {
                        log.error("20032:license授权域名不合法");
                        throw new CustomException(ApiResult.result(20032));
                    }
                }
            }
        }
    }


    private boolean ignore(String domain) {
        boolean ignore = (domain.startsWith("http://127") || domain.startsWith("127") || domain.contains("localhost"));
        return ignore;
    }
}
