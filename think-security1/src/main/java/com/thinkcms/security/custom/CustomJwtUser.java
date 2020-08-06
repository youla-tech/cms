package com.thinkcms.security.custom;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomJwtUser extends User {
    private String userId;
    private String deptId;
    private String userName;
    private String name;
    private Set<String> roleSigns;

    public CustomJwtUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public CustomJwtUser setDeptId(String deptId) {
        this.deptId = deptId;
        return this;
    }

    public CustomJwtUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public CustomJwtUser setName(String name) {
        this.name = name;
        return this;
    }

    public CustomJwtUser setRoleSigns(Set<String> roleSigns) {
        this.roleSigns = roleSigns;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomJwtUser)) return false;
        CustomJwtUser other = (CustomJwtUser) o;
        if (!other.canEqual(this)) return false;
        Object this$userId = getUserId(), other$userId = other.getUserId();
        if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;
        Object this$deptId = getDeptId(), other$deptId = other.getDeptId();
        if ((this$deptId == null) ? (other$deptId != null) : !this$deptId.equals(other$deptId)) return false;
        Object this$userName = getUserName(), other$userName = other.getUserName();
        if ((this$userName == null) ? (other$userName != null) : !this$userName.equals(other$userName)) return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;
        Object this$roleSigns = getRoleSigns(), other$roleSigns = other.getRoleSigns();
        return !((this$roleSigns == null) ? (other$roleSigns != null) : !this$roleSigns.equals(other$roleSigns));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CustomJwtUser;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userId = getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        Object $deptId = getDeptId();
        result = result * 59 + (($deptId == null) ? 43 : $deptId.hashCode());
        Object $userName = getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        Object $name = getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        Object $roleSigns = getRoleSigns();
        return result * 59 + (($roleSigns == null) ? 43 : $roleSigns.hashCode());
    }

    public String toString() {
        return "CustomJwtUser(userId=" + getUserId() + ", deptId=" + getDeptId() + ", userName=" + getUserName() + ", name=" + getName() + ", roleSigns=" + getRoleSigns() + ")";
    }


    public String getUserId() {
        return this.userId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getName() {
        return this.name;
    }

    public Set<String> getRoleSigns() {
        return this.roleSigns;
    }

    public CustomJwtUser(String username, String password, Collection<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
