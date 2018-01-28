package com.choice.shiro.realms;


import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.log4j.ConsoleAppender;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.ehcache.core.spi.time.SystemTimeSource;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import sun.security.krb5.Credentials;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jack on 2017-12-10.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("First---doGetAuthenticationInfo"+token.hashCode());

        //1.token转换为UsernamePasswordToken
        UsernamePasswordToken utoken  = (UsernamePasswordToken) token;

        //2 获取用户
        String user  = utoken.getUsername();
        //3 查询数据库中的用户
        System.out.println("数据库中的用户名是"+user );

        //4 用户不存在 抛异常 UnknownAccountException  用户锁定 抛异常LockedAccountException,还有异常 属于其他的AuthenticationException异常
        if("unKnow".equals(user)){
            throw new UnknownAccountException("无此用户");
        }

        // 5 构建AuthenticationInfo 进行比较  并返回对象
        if("monster".equals(user)){
            throw new LockedAccountException("用户被锁定");
        }
        // 1 principal可以是：认证的实体信息 也可以是username ,也可以是认证的user的实体信息
        Object principal = user;
        //2 credentials: 密码


        Object credentials = null;/*= "64c8b1e43d8ba3115ab40bcea57f010b"*/;
        if("admin".equals(user)){
            credentials="c41d7c66e1b8404545aa3a0ece2006ac";
        }else if("user".equals(user)){
            credentials="2bbffae8c52dd2532dfe629cecfb2c85";
        }

        //3 realmName: 就是此realm的 name  直接调用父类的 getName() 方法即可
        String realmName = getName();

        //盐
        ByteSource sault  = ByteSource.Util.bytes(user);


        //下面的方法主要一个作用  就是根据数据库的密码  加盐加密
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( principal,credentials, sault, realmName);


        return info;
    }

    public static void main(String[] args) {
        String haalgorithmName = "MD5";
         Object source = "123";
         ByteSource sault  = ByteSource.Util.bytes("admin");
         int hashIteratio = 1024;


        /*SimpleHash simpleHash = new SimpleHash(haalgorithmName, source, salt, hashIteratio);*/
        SimpleHash simpleHash = new SimpleHash( haalgorithmName,  source,  sault,  hashIteratio);
        System.out.println(simpleHash);
    }

    //授权使用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 1 获取用户信息
        Object principal = principalCollection.getPrimaryPrincipal();

        //2 根据用户信息  给用户赋权（可能需要查询数据库）
        Set<String> roles = new HashSet<String>();


        if("admin".equals(principal)){
            roles.add("admin");
        }
        roles.add("user");
        //3 创建 SimpleAuthenticationInfo  设置realm属性
        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo(roles);

        //4 返回info对象
        return info;
    }
}
