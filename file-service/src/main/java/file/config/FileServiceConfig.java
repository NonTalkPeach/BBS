package file.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import java.io.File;
@Slf4j
@Configuration
public class FileServiceConfig {
    @Value("${myconfig.public-file-location}")
    private String publicFileLocation;

    @Value("${myconfig.file-location}")
    private String fileLocation;

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    @Bean
    public void initBean() {
        File avatarImgFile  = new File(publicFileLocation + "/avatarImg");
        if (!avatarImgFile.exists()) avatarImgFile.mkdirs();
        File blogImgFile  = new File(publicFileLocation + "/blogImg");
        if (!blogImgFile.exists()) blogImgFile.mkdirs();
        File file  = new File(fileLocation + "/resources");
        if (!file.exists()) file.mkdirs();
        log.info("[Tao 's words] : init dir successfully!");
    }
}
