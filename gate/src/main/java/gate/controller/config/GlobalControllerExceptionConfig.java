package gate.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常统一处理
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionConfig {

    @ExceptionHandler(value = Exception.class)
    public String globalExceptionCatch(Exception ex, Model model) {
        log.error("[Tao 's words] : "+ex.toString());
        if (ex.getMessage().equals("IllegalToken")) {
            model.addAttribute("msg","IllegalToken!请重新登录！");
        } else {
            model.addAttribute("msg","程序出现错误，请联系管理员");
        }
        return "message";
    }
}
