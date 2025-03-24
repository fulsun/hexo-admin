# Hexo博客管理系统

## 项目概述
基于Spring Boot构建的现代化博客管理系统，提供文章管理、图片管理、评论审核等后台管理功能，前端采用AdminLTE管理模板。

## 功能特性
- 可视化文章编辑器(Markdown支持)
- 图片云存储管理

- 用户权限管理
- 数据统计看板

## 技术栈
- 后端：Spring Boot 2.7 + MyBatis Plus
- 前端：AdminLTE 3 + Thymeleaf + jQuery
- 数据库：MySQL 8.0

## 快速启动
1. 安装Java 17+和Maven 3.8+
2. 导入MySQL数据库：
```bash
mysql -u root -p < init.sql
```
3. 配置application.yml数据库连接信息
4. 运行项目：
```bash
mvn spring-boot:run
```

## 接口文档
- Swagger文档：http://localhost:8080/swagger-ui.html
- 示例请求：
```
GET /api/articles?page=1&size=10
```

## 贡献指南
欢迎提交PR，请遵循以下规范：
1. 功能开发基于feature分支
2. 提交前执行mvn test
3. 保持代码风格统一

## 开源协议
MIT License