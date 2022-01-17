package file.aop;

import base.correspond.CorrespondBean;
import base.correspond.ForwardUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Aspect
public class SecurityAspect {
    @Autowired
    RestTemplate restTemplate;
    private static final String REST_URL_PREFIX_AUTH = "http://AUTH-SERVICE/api/auth";

    @Pointcut("execution(* file.controller.UploadController.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void isLogin(JoinPoint joinPoint) throws Exception {
        String token = (String) joinPoint.getArgs()[0];
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_AUTH + "/toVerify", ForwardUtil.getKeyValueMapForOneParam("token", token), CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.FAIL) {
            throw new RuntimeException("IllegalToken");
        }
    }
}
