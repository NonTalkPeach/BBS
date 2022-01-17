package file.controller.config;

import base.correspond.CorrespondBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常统一处理
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public CorrespondBean globalExceptionCatch(Exception ex) {
        CorrespondBean correspondBean;
        log.error("[Tao 's words] : "+ex.toString());
        if (ex.getMessage().equals("IllegalToken")) {
            correspondBean = CorrespondBean.getFailBean("非法令牌！");
        } else {
            correspondBean = CorrespondBean.getFailBean("程序出现错误，请联系管理员");
        }
        return correspondBean;
    }
}
