package auth.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToRegisterController {
    @PostMapping("/toRegister")
    public String toRegister(){
        return null;
    }
}
