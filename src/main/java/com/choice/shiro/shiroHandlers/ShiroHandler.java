package com.choice.shiro.shiroHandlers;

import com.choice.shiro.services.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by jack on 2017-12-12.
 */
@Controller
@ControllerAdvice
@RequestMapping("/shiro")
public class ShiroHandler {
    @Autowired
    private ShiroService shiroService;

    @ExceptionHandler(RuntimeException.class)
    @RequestMapping("/testAnnontation")
    public String testAnnontation(HttpSession session){
        session.setAttribute("key","value123");
        shiroService.testMethod();
        return "redirect:/view/list.jsp";
    }


    @RequestMapping("/login")
    String login(@RequestParam("username") String username,@RequestParam("password") String password){

        Subject sb =  SecurityUtils.getSubject();


        if(!sb.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            //真正开发过程中，一般登录会在右边设置一个“记住我选项”，当勾选以后 我们再进行remeneber me
            token.setRememberMe(true);
            //尝试进行登录用户，如果登录失败了，我们进行一些处理
            try {
                sb.login(token);

            } catch(AuthenticationException aoe){
                System.out.println("登录失败"+aoe.getMessage());
            }
        }
        return "redirect:/view/list.jsp";
    }
}
