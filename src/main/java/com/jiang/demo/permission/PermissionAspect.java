package com.jiang.demo.permission;

import com.jiang.demo.entity.Tokens;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.TokenRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.core.annotation.Order;
import java.lang.reflect.Method;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: 江云飞
 * Date:   2019/4/16
 * 权限验证
 */

/* @Permission
    @Login
*/
@Order(2)
@Aspect
@Component
public class PermissionAspect {

    //打印日志信息
    private final static Logger logger= LoggerFactory.getLogger(PermissionAspect.class);


    @Autowired
    private TokenRepository tokenRepository;


    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.jiang.demo.controller.*.*(..))"+"&& !execution(public * com.jiang.demo.controller.UserInfoController.*(..))")
    public void privilege(){

    }

    /**
     * 权限环绕通知
     *
     * @param joinPoint
     * @throws Throwable
     */

    @ResponseBody
    @Around("privilege()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable{

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        //得到方法的访问权限
        final String methodAccess = AnnotationParse.privilegeParse(targetMethod);
        System.out.println("访问的权限："+methodAccess);
        //如果该方法上没有权限注解，直接调用目标方法
        if (StringUtils.isEmpty(methodAccess)) {
            System.out.println("该方法上没有管理员权限注解");
            return joinPoint.proceed();
        } else {
            //如果该方法上有注解
            Cookie[] cookies = request.getCookies();
            try {

                String token=cookies[0].getValue();
                System.out.println("token==="+cookies[0].getName());
                System.out.println("token==="+token);
                //根据token密匙查询 信息
                try {
                    Tokens tokens = tokenRepository.findTokensByToken(token);

                    String username = tokens.getUserInfo().getUsername();
                    System.out.println("username" + username);

                    //根据用户名获取角色名
                    String roleName = tokenRepository.getRoleName(username);
                    if(roleName.equals("administrators")){
                        logger.info("您是超级管理员");
                        //是管理员时，继续执行方法 返回数据
                        return joinPoint.proceed();
                    }else {
                        throw new MyException(-2,"您不是超级管理员");
                    }
                }catch (Exception e){
                    throw new MyException(-3, "你还没登陆！");
                }
               /* Integer token1 = tokenRepository.getToken(token);
                if(token1<1){
                    throw new MyException(-4, "你还没登陆！");
                }*/

            }catch (Exception e){
                throw new MyException(-3, "您还未登陆！");
            }

        }
    }
}
