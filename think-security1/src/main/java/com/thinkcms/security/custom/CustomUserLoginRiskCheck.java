package com.thinkcms.security.custom;

import com.thinkcms.core.api.BaseRedisService;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.handler.CustomException;
import com.thinkcms.core.utils.ApiResult;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.security.license.VerifyLicense;
import com.thinkcms.security.license.VerifyLicenseGenProxy;
import com.thinkcms.security.license.VerifyLicenseImpl;
import com.thinkcms.system.api.system.UserService;
import com.thinkcms.system.dto.system.UserDto;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomUserLoginRiskCheck {
  @Autowired
  UserService userService;
  @Autowired
  BaseRedisService baseRedisService;
  @Autowired
  ThinkCmsConfig cmsConfig;
  
  public UserDto loginRiskCheck(String userName) throws CustomException {
     checkMaxError(userName);
     UserDto userDto = null;
     if (Checker.BeNotBlank(userName).booleanValue()) {
       userDto = this.userService.findUserByUsername(userName);
       if (Checker.BeNotNull(userDto)) {
         Set<String> roleSigns = this.userService.selectRoleSignByUserId(userDto.getId());
         if (Checker.BeNotEmpty(roleSigns).booleanValue()) {
           userDto.setRoleSigns(roleSigns);
        }
      } 
    } 
    return userDto;
  }

  
  public void writeErrorLog(String userName) {
     String key = "error_input_pass_times_" + userName;
     long expireTime = this.baseRedisService.getExpire(key);
     if (expireTime == -1L) {
       this.baseRedisService.increment(key, 1, 600L);
    } else {
       this.baseRedisService.increment(key, 1);
    } 
  }
  
  private void checkMaxError(String userName) throws CustomException {
     String key = "error_input_pass_times_" + userName;
     if (this.baseRedisService.hasKey(key).booleanValue()) {
       Integer times = (Integer)this.baseRedisService.get(key);
       if (times.intValue() >= 6) {
         long expireTime = this.baseRedisService.getExpire(key);
         if (expireTime == -1L) {
           this.baseRedisService.setExpireTime("error_input_pass_times_" + userName, Long.valueOf(300L));
        }
         throw new CustomException(ApiResult.result(7001));
      } 
    } 
  }
  
  public void checkerLicense() throws Exception {
    try {
       VerifyLicense verifyLicense = (VerifyLicense)VerifyLicenseGenProxy.proxyVerifyLicense(new VerifyLicenseImpl(this.cmsConfig, this.baseRedisService));
       verifyLicense.verify(this.cmsConfig.getLicensePath(), null);
     } catch (Exception e) {
       throw e;
    } 
  }
}

