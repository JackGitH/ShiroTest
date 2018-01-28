package com.choice.shiro.realms;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jack on 2017-12-28.
 */
@Controller
public class AccessController {

    /**
     * 异常页面控制
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String runtimeExceptionHandler(RuntimeException runtimeException,
                                          ModelMap modelMap) {
        modelMap.put("status", "false");
        return "exception";
    }
}
