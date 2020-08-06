package com.thinkcms.security.custom;

import com.thinkcms.system.api.system.MenuService;
import com.thinkcms.system.api.system.UserService;
import com.thinkcms.system.dto.system.UserDto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class CustomUserDetailsService
        implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    CustomUserLoginRiskCheck customUserLoginRiskCheck;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDto userDto = this.customUserLoginRiskCheck.loginRiskCheck(userName);
        if (userDto == null) {
            throw new UsernameNotFoundException("账号或密码不正确!");
        }
        if (userDto.getStatus().intValue() == 1) {
            throw new LockedException("账号已被锁定,请联系管理员");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        CustomJwtUser customJwtUser = new CustomJwtUser(userDto.getUsername(), userDto.getPassword(), grantedAuthorities);
        customJwtUser.setUserName(userDto.getUsername()).setName(userDto.getName()).setUserId(userDto.getId())
                .setDeptId(userDto.getOrgId()).setRoleSigns(userDto.getRoleSigns());
        return (UserDetails) customJwtUser;
    }
}
