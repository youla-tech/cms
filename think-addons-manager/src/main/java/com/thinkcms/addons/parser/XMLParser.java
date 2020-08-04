package com.thinkcms.addons.parser;
import com.thinkcms.addons.model.Plugin;
import com.thinkcms.core.config.ThinkCmsConfig;
import com.thinkcms.core.utils.FileUtil;
import com.thinkcms.core.utils.SpringContextHolder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class XMLParser {

    static ThinkCmsConfig thinkCmsConfig = SpringContextHolder.getBean(ThinkCmsConfig.class);

    static List<Plugin> pluginList=new ArrayList<>(16);

    public static List<Plugin> parsePlugins() throws DocumentException, FileNotFoundException {
        if(!pluginList.isEmpty()){
            return pluginList;
        }
        List<Plugin> list = new ArrayList<Plugin>();
        SAXReader saxReader = new SAXReader();
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"plugins.xml");
        Document document = saxReader.read(file);
        Element root = document.getRootElement();
        List<?> plugins = root.elements("plugin");
        for (Object pluginObj : plugins) {
            Element pluginEle = (Element) pluginObj;
            String jar = thinkCmsConfig.getPluginsBasePath()+File.separator+pluginEle.elementText("jar");
            if(pluginIsExist(jar)){
                Plugin plugin = new Plugin();
                plugin.setName(pluginEle.elementText("name"));
                plugin.setJar(pluginEle.elementText("jar"));
                plugin.setClassName(pluginEle.elementText("class"));
                plugin.setAuthor(pluginEle.elementText("author"));
                plugin.setVersion(pluginEle.elementText("version"));
                plugin.setSignaturer(pluginEle.elementText("signaturer"));
                list.add(plugin);
            }
        }
        pluginList.addAll(list);
        return pluginList;
    }

    static List<Plugin> reloadPlugins() throws FileNotFoundException, DocumentException {
        pluginList.clear();
        parsePlugins();
        return pluginList;
    }

    static boolean pluginIsExist(String jar){
        return FileUtil.exists(jar);
    }
}