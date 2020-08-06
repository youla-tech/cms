package com.thinkcms.security.custom;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

public class CustomAuthencationOk
  implements ApplicationListener<AuthenticationSuccessEvent>
{
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
      System.out.println(event.getSource().getClass());
  }
}
