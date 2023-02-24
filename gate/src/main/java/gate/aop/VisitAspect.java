package gate.aop;

import gate.redis.RedisOpsForString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

/**
 * 使用反射辩别参数的传递
 * 接收Model 类参数
 * 该AOP 用于 访问量等信息的获取
 */
@Aspect
@Component
public class VisitAspect {
    @Autowired
    RedisOpsForString redisOpsForString;

    @Autowired
    RestTemplate restTemplate;
    private static final String REST_URL_PREFIX_IP = "https://freeapi.ipip.net";

    @Pointcut(
            "execution(* gate.controller.HomeController.*(..)) || " +
            "execution(* gate.controller.LoginRegisterController.*(..)) || " +
            "execution(* gate.controller.logined.LoginedUploadViewController.uploadPublicFile(..)) || " +
            "execution(* gate.controller.logined.LoginedViewController.*(..))"
    )
    public void pointCut(){

    }
    @Pointcut("execution(* gate.controller.config.*.*(..))")
    public void pointCutToGlobal(){

    }

    @Before("pointCut()")
    public void checkAndAddVisitNum(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model)arg;
                model.addAttribute("visitorNum",redisOpsForString.increase("visitorNum"));
            }
        }
    }
    @Before("pointCutToGlobal()")
    public void checkVisitNum(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Model) {
                Model model = (Model)arg;
                model.addAttribute("visitorNum",redisOpsForString.getString("visitorNum"));
            }
        }
    }
}
