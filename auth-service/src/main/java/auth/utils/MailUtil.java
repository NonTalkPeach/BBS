package auth.utils;

import base.correspond.CorrespondBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${myconfig.web-url}")
    private String webUrl;

    @Async
    public CorrespondBean sendCode(String email,String code){
        String url = webUrl+"/toActive/"+code;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("鱼水狩·注册激活链接");
        simpleMailMessage.setText("嘻嘻嘻终于等到您的到来啦!\n" +
                "请点击下面的链接来激活您的账户\n"+
                url);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("245149633@qq.com");
        mailSender.send(simpleMailMessage);
        return CorrespondBean.getSuccessBean("发送成功");
    }
}
