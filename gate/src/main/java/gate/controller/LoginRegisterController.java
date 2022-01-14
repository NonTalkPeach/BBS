package gate.controller;

import base.correspond.CorrespondBean;
import gate.utils.ForwardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginRegisterController {
    @Autowired
    RestTemplate restTemplate;
    private static final String REST_URL_PREFIX_AUTH = "http://AUTH-SERVICE/api/auth";

    @PostMapping("/toLogin")
    public String toLogin (HttpServletRequest request, Model model) {
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_AUTH + "/toLogin", ForwardUtil.getKeyValueMapForParams(request), CorrespondBean.class);
        model.addAttribute("msg",correspondBean.getMessage());
        if (correspondBean.getCode() == CorrespondBean.FAIL) {
            return "login";
        } else {
            model.addAttribute("token",correspondBean.getData());
            return "index";
        }
    }

    @PostMapping("/toRegister")
    public String toRegister (HttpServletRequest request, Model model) {
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_AUTH + "/toRegister", ForwardUtil.getKeyValueMapForParams(request), CorrespondBean.class);
        model.addAttribute("msg",correspondBean.getMessage());
        if (correspondBean.getCode() == CorrespondBean.SUCCESS) {
            return "message";
        } else {
            return "register";
        }
    }

    @RequestMapping("/toActive/{userCode}")
    public String toActive(@PathVariable("userCode") String userCode, Model model) {
        CorrespondBean correspondBean = restTemplate.getForObject(REST_URL_PREFIX_AUTH + "/toActive/" + userCode, CorrespondBean.class);
        model.addAttribute("msg",correspondBean.getMessage());
        return "message";
    }
}
