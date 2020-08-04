package com.thinkcms.service.service.plugin;

import com.thinkcms.addons.manager.PluginManager;
import com.thinkcms.addons.parser.XMLParser;
import com.thinkcms.addons.service.PluginService;
import com.thinkcms.core.utils.Checker;
import com.thinkcms.service.dto.plugin.PluginInterModel;

public abstract class PluginInterpreter {

    public PluginService interpreter(PluginInterModel pluginInterModel)  {
        if(check(pluginInterModel)){
            String signaturer=pluginInterModel.getSignaturer();
            try {
                PluginManager pluginManager=new PluginManager(XMLParser.parsePlugins());
                PluginService pluginService=pluginManager.getInstanceBySignaturer(signaturer);
                return pluginService;
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    private boolean check(PluginInterModel pluginInterModel){
        if(Checker.BeNull(pluginInterModel)){
            return false;
        }
        if(Checker.BeBlank(pluginInterModel.getSignaturer())){
            return false;
        }
        if(Checker.BeBlank(pluginInterModel.getApi())){
            return false;
        }
        if(Checker.BeBlank(pluginInterModel.requestMethod)){
            pluginInterModel.setRequestMethod("GET");
        }
        return true;
    }
}
