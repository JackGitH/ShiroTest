package com.choice.shiro.factory;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author  jack
 * @Created by jack on 2017-12-28.
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String>  buildFilterChainDefinitionMap(){
        Date date  = new Date ();
        LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
        map.put("/login.jsp","anon");
        map.put("/shiro/login","anon");
        map.put("/shiro/logout","logout");
        map.put("/view/user.jsp","authc,roles[user]") ;
        map.put("/view/admin.jsp","authc,roles[admin]") ;
        map.put("/view/list.jsp","user") ;

        map.put("/**","authc");
        return map;
    }
}
