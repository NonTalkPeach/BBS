package gate.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/api")
public class AuthServiceAPI {
    @PostMapping("/toLogin")
    public String toLogin(){
        return null;
    }
}
