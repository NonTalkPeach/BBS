package auth.controller.config;

import base.correspond.CorrespondBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionConfig {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CorrespondBean globalExceptionCatch(Exception ex) {
        log.error(ex.toString());
        return CorrespondBean.getFailBean("程序出现错误。请联系管理员。");
    }
}
