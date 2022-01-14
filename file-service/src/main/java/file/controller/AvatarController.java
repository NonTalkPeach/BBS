package file.controller;

import base.correspond.CorrespondBean;
import file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/file")
@ResponseBody
public class AvatarController {
    @Autowired
    UserService userService;

    @Value("${myconfig.filelocation}")
    private String fileLocation = "1";

    @PostMapping("/uploadAvatar")
    public CorrespondBean uploadAvatar(@RequestParam("avatarFile") CommonsMultipartFile file, String userCode) throws IOException {
        String path =  "/avatarImg/" + userCode + ".jpg";
        file.transferTo(new File(fileLocation + path));
        userService.updateUserAvatar(userCode,path);
        return CorrespondBean.getSuccessBean("头像上传成功！",path);
    }
}
