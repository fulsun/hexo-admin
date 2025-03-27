package pers.fulsun.hexoadmin.web.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import pers.fulsun.hexoadmin.web.handle.GlobalWebExceptionHandler;

@AutoConfiguration
@ConditionalOnWebApplication
public class WebConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    GlobalWebExceptionHandler globalWebExceptionHandler() {
        return new GlobalWebExceptionHandler();
    }
    
}
