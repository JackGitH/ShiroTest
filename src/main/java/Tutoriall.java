
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;


/**
 * Created by jack on 2017-12-04
 */
public class Tutoriall {
   private static  final transient Logger log = LoggerFactory.getLogger(Tutoriall.class);

    public static void main(String[] args) {
        log.info("MyFirst Apache Shiro Application");
        //加载配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //解析配置文件 返回实例
        SecurityManager securitymanager = factory.getInstance();
        //设置SecurityManager到静态内存区，单例模式
        SecurityUtils.setSecurityManager(securitymanager);
        //安全操作
        Subject sb  = SecurityUtils.getSubject();
        //设置回话属性
        Session session = sb.getSession();
        //设置属性
        session.setAttribute("key","value");

         if(!sb.isAuthenticated()){
             UsernamePasswordToken token = new UsernamePasswordToken("aihe","aihe");
             token.setRememberMe(true);
             //尝试进行登录用户，如果登录失败了，我们进行一些处理
             try {
                 sb.login(token);
                 log.info("User["+sb.getPreviousPrincipals()+"]logged in successfully");
                 if(sb.hasRole("client")){
                     log.info("Look is in your role");
                 }else{
                     log.info("-----");
                 }
                 if(sb.isPermitted("look:desk")){
                     log.info("you can look");
                 }else{
                     log.info("sorrt you cant look");
                 }
                 if(sb.isPermitted("winnebago:drive:eagle5")){
                     log.info("you can peimitted to drive the winnebao");
                 }else{
                     log.info("you  arent allowed to dirve");
                 }
                 //登出
                 sb.logout();
             }catch(UnknownAccountException uae){

             }catch (IncorrectCredentialsException ice){

             }catch(LockedAccountException lae ){

             }catch(AuthenticationException aoe){

             }

         }
        System.exit(0);
    }
}
