package com.thinkcms.security.properties;


import java.util.List;

public class Application {
    public String appName;

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> authc;
    public List<String> ignore;

    public void setAuthc(List<String> authc) {
        this.authc = authc;
    }

    public void setIgnore(List<String> ignore) {
        this.ignore = ignore;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Application)) return false;
        Application other = (Application) o;
        if (!other.canEqual(this)) return false;
        Object this$appName = getAppName(), other$appName = other.getAppName();
        if ((this$appName == null) ? (other$appName != null) : !this$appName.equals(other$appName)) return false;
        Object this$authc = (Object) getAuthc(), other$authc = (Object) other.getAuthc();
        if ((this$authc == null) ? (other$authc != null) : !this$authc.equals(other$authc)) return false;
        Object this$ignore = (Object) getIgnore(), other$ignore = (Object) other.getIgnore();
        return !((this$ignore == null) ? (other$ignore != null) : !this$ignore.equals(other$ignore));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Application;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $appName = getAppName();
        result = result * 59 + (($appName == null) ? 43 : $appName.hashCode());
        Object $authc = (Object) getAuthc();
        result = result * 59 + (($authc == null) ? 43 : $authc.hashCode());
        Object $ignore = (Object) getIgnore();
        return result * 59 + (($ignore == null) ? 43 : $ignore.hashCode());
    }

    public String toString() {
        return "Application(appName=" + getAppName() + ", authc=" + getAuthc() + ", ignore=" + getIgnore() + ")";
    }

    public String getAppName() {
        return this.appName;
    }

    public List<String> getAuthc() {
        return this.authc;
    }

    public List<String> getIgnore() {
        return this.ignore;
    }
}
