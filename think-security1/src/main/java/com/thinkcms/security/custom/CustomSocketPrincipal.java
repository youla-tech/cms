package com.thinkcms.security.custom;

import java.security.Principal;

public class CustomSocketPrincipal implements Principal {
    private String userName;
    private String id;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomSocketPrincipal)) return false;
        CustomSocketPrincipal other = (CustomSocketPrincipal) o;
        if (!other.canEqual(this)) return false;
        Object this$userName = getUserName(), other$userName = other.getUserName();
        if ((this$userName == null) ? (other$userName != null) : !this$userName.equals(other$userName)) return false;
        Object this$id = getId(), other$id = other.getId();
        return !((this$id == null) ? (other$id != null) : !this$id.equals(other$id));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CustomSocketPrincipal;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $userName = getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        Object $id = getId();
        return result * 59 + (($id == null) ? 43 : $id.hashCode());
    }

    public String toString() {
        return "CustomSocketPrincipal(userName=" + getUserName() + ", id=" + getId() + ")";
    }

    public String getUserName() {
        return this.userName;
    }

    public String getId() {
        return this.id;
    }

    public CustomSocketPrincipal(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getName() {
        return getId();
    }
}
