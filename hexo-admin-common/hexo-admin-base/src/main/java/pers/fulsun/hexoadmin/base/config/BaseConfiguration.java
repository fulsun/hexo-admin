package pers.fulsun.hexoadmin.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.fulsun.hexoadmin.base.utils.SpringContextHolder;

@Configuration
public class BaseConfiguration {
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
