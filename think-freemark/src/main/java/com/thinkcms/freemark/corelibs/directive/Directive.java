package com.thinkcms.freemark.corelibs.directive;

import com.thinkcms.core.constants.DirectiveNameEnum;
import com.thinkcms.freemark.corelibs.handler.RenderHandler;

import java.io.IOException;

/**
 * 
 * BaseDirective 指令接口
 *
 */
public interface Directive {
    /**
     * @return name
     */
     DirectiveNameEnum getDirectiveName();

    /**
     * @param handler
     * @throws IOException
     * @throws Exception
     */
     void execute(RenderHandler handler) throws IOException, Exception;
}
