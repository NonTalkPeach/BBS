package bbs.config;

import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro安全框架的配置
 * 向注入IOC注入配置bean
 */
@Configuration
public class ShiroConfig {
    /**
     *
     * @param shiroRealm
     * 规则域
     * @return
     * 注入到IOC中
     */
    @Bean
    public ShiroRealm shiroRealm(ShiroRealm shiroRealm){
        return new ShiroRealm();
    }

    @Bean
    public SecurityManager configSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        return null;
    }
}
