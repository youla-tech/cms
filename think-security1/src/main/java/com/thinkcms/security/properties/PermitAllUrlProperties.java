package com.thinkcms.security.properties;

import com.thinkcms.core.utils.Checker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "permission")
public class PermitAllUrlProperties {
    public void setApplication(List<Application> application) {
        this.application = application;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PermitAllUrlProperties)) return false;
        PermitAllUrlProperties other = (PermitAllUrlProperties) o;
        if (!other.canEqual(this)) return false;
        Object this$application = (Object) getApplication(), other$application = (Object) other.getApplication();
        return !((this$application == null) ? (other$application != null) : !this$application.equals(other$application));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PermitAllUrlProperties;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $application = (Object) getApplication();
        return result * 59 + (($application == null) ? 43 : $application.hashCode());
    }

    public String toString() {
        return "PermitAllUrlProperties(application=" + getApplication() + ")";
    }


    public List<Application> application = new ArrayList<>();

    public List<Application> getApplication() {
        return this.application;
    }

    public boolean ckTheSameApi(String name, String apiUrl) {
        if (Checker.BeBlank(name).booleanValue()) {
            return false;
        }
        boolean res = false;
    
        label18:
        for (Application app : getApplication()) {
            if (name.equals(app.getAppName())) {
                for (String api : app.getAuthc()) {
                    if (apiUrl.equals(api)) {
                        res = true;
                        break label18;
                    }
                }
            }
        }
        return res;
    }

    public List<String> ignores() {
        List<String> ignores = new ArrayList<>(16);
        if (Checker.BeNotEmpty(this.application).booleanValue()) {
            this.application.forEach(app -> ignores.addAll(app.getIgnore()));
        }
        return ignores;
    }
}
