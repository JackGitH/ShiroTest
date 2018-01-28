package com.choice.shiro.services;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jack on 2017-12-28.
 */

public class ShiroService {
    @RequiresRoles({"admin"})
    public void testMethod(){
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("HttpSession中的value是"+session.getAttribute("key"));
        System.out.println("testMethod");
    }


}
