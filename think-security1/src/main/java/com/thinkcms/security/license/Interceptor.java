package com.thinkcms.security.license;

import java.lang.reflect.Method;
import java.util.Map;

public interface Interceptor {
  Map<String, Object> before(Object paramObject1, Object paramObject2, Method paramMethod, Object[] paramArrayOfObject);
  
  void around(Object paramObject1, Object paramObject2, Method paramMethod, Object[] paramArrayOfObject);
  
  void after(Object paramObject1, Object paramObject2, Method paramMethod, Object[] paramArrayOfObject, Map<String, Object> paramMap);
}
