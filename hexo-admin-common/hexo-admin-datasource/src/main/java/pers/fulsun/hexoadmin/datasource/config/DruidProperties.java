package pers.fulsun.hexoadmin.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProperties {
    
    private String url;
    
    private String username;
    
    private String password;
    
    private String driverClassName;
    
    // Druid 特定配置
    private Druid druid = new Druid();
    
    @Data
    public static class Druid {
        
        private int initialSize;
        
        private int minIdle;
        
        private int maxActive;
        
        private long maxWait;
        
        private long timeBetweenEvictionRunsMillis;
        
        private long minEvictableIdleTimeMillis;
        
        private String validationQuery;
        
        private boolean testWhileIdle;
        
        private boolean testOnBorrow;
        
        private boolean testOnReturn;
        
        private boolean poolPreparedStatements;
        
        private int maxPoolPreparedStatementPerConnectionSize;
    }
} 