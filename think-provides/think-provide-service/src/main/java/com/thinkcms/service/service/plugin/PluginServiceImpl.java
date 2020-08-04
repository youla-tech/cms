package com.thinkcms.service.service.plugin;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.service.api.plugin.PluginService;
import com.thinkcms.service.dto.plugin.PluginInterModel;
import org.springframework.stereotype.Service;

@Service
public class PluginServiceImpl extends PluginInterpreter implements PluginService {

    @Override
    public void doIt(PluginInterModel pluginInter) {
        com.thinkcms.addons.service.PluginService interpreter=interpreter(pluginInter);
        if(Checker.BeNotNull(interpreter)){
            interpreter.execute(pluginInter.getApi(),pluginInter.getRequestMethod(),pluginInter.getParams());
        }
    }

}
