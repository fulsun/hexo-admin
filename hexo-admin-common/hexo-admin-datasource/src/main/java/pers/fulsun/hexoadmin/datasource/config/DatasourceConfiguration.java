package pers.fulsun.hexoadmin.datasource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.fulsun.hexoadmin.datasource.handle.DataObjectHandler;

@Configuration
@MapperScan(basePackages = "pers.fulsun.hexoadmin.*.infrastructure.mapper")
public class DatasourceConfiguration {
    /**
     * 数据库字段自动填充
     * @return
     */
    @Bean
    public DataObjectHandler dataObjectHandler() {
        return new DataObjectHandler();
    }
}
