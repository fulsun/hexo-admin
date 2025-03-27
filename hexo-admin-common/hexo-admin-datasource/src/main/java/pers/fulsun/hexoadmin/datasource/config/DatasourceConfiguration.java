package pers.fulsun.hexoadmin.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pers.fulsun.hexoadmin.datasource.handle.DataObjectHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@Slf4j
@MapperScan(basePackages = "pers.fulsun.hexoadmin.*.infrastructure.mapper")
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DatasourceConfiguration {
    
    private final DruidProperties properties;
    
    public DatasourceConfiguration(DruidProperties properties) {
        this.properties = properties;
    }
    
    /**
     * 数据库字段自动填充.
     *
     * @return DataObjectHandler
     */
    @Bean
    public DataObjectHandler dataObjectHandler() {
        return new DataObjectHandler();
    }
    
    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        try {
            createDatabase();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database", e);
        }
        
        // 设置基本属性
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());
        datasource.setDriverClassName(properties.getDriverClassName());
        
        // 设置Druid特定属性
        DruidProperties.Druid druid = properties.getDruid();
        datasource.setInitialSize(druid.getInitialSize());
        datasource.setMinIdle(druid.getMinIdle());
        datasource.setMaxActive(druid.getMaxActive());
        datasource.setMaxWait(druid.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druid.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druid.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druid.getValidationQuery());
        datasource.setTestWhileIdle(druid.isTestWhileIdle());
        datasource.setTestOnBorrow(druid.isTestOnBorrow());
        datasource.setTestOnReturn(druid.isTestOnReturn());
        datasource.setPoolPreparedStatements(druid.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druid.getMaxPoolPreparedStatementPerConnectionSize());
        
        return datasource;
    }
    
    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:db").baselineOnMigrate(true)
                .load();
        flyway.migrate();  // 执行迁移
        return flyway;     // 返回 Flyway 实例
    }
    
    private void createDatabase() throws SQLException {
        String dbName = extractDatabaseName(properties.getUrl());
        String rootUrl = properties.getUrl().replace("/" + dbName, "/");
        try (Connection conn = DriverManager.getConnection(rootUrl, properties.getUsername(), properties.getPassword());
                Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE DATABASE IF NOT EXISTS `" + dbName + "`");
            log.info("Database '{}' checked/created successfully", dbName);
        }
    }
    
    private String extractDatabaseName(String url) {
        String cleanUrl = url.replaceFirst("jdbc:mysql://", "");
        int slashIndex = cleanUrl.indexOf('/');
        int questionMarkIndex = cleanUrl.indexOf('?');
        return questionMarkIndex == -1 ? cleanUrl.substring(slashIndex + 1)
                : cleanUrl.substring(slashIndex + 1, questionMarkIndex);
    }
}
