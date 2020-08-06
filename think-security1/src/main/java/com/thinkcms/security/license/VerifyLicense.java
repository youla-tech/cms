package com.thinkcms.security.license;

import java.util.Map;

public interface VerifyLicense {
  Boolean verify(String paramString, Map<String, Object> paramMap) throws Exception;
}