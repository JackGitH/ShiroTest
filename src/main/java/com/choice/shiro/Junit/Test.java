package com.choice.shiro.Junit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jack on 2018-01-28.
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext ("application-child.xml");
        Child child  =  context.getBean (Child.class);
        System.out.println (child.getAge ());
        System.out.println (child.getName ());
    }
}
