# 在IDEA中配置Nacos代码风格指南

## 一、代码风格配置

### 1. 导入Nacos代码风格模板

1. 下载源码中的风格文件  
   `style/nacos-code-style-for-idea.xml`

2. IDEA导入步骤：
    - 打开设置：`File -> Settings`（Windows） / `IntelliJ IDEA -> Preferences`（Mac）
    - 导航至：`Editor -> Code Style`
    - 点击右上角齿轮图标 -> `Import Schema -> IntelliJ IDEA code style XML`
    - 选择下载的nacos-code-style-for-idea.xml文件

3. 验证配置生效：
    - 新建Java文件输入测试代码
    - 使用`Ctrl+Alt+L`格式化代码
    - 观察缩进应为4空格，方法参数换行等特征

## 二、代码质量插件（可选）

### 1. Alibaba代码规约插件（推荐）

▶ 安装步骤：

1. 插件市场搜索"Alibaba Java Coding Guidelines"
2. 安装后重启IDEA

▶ 使用说明：

- 实时检测：编码时会在问题代码处显示黄色波浪线
- 手动扫描：右键项目/目录 -> 选择"编码规约扫描"

### 2. CheckStyle插件配置

▶ 安装步骤：

1. 插件市场搜索"CheckStyle-IDEA"并安装
2. 重启IDEA

▶ 配置方法：

1. 下载配置文件：
    - 主配置：`style/NacosCheckStyle.xml`
    - (如遇版本问题) 备用配置：`style/NacosCheckStyle_9.xml`

2. 插件设置：
    - `Settings -> Tools -> Checkstyle`
    - 点击"+" -> 选择下载的配置文件
    - 勾选"Active"激活配置
    - 设置Scan Scope为"All resource(including tests)"

▶ 使用方式：

- 右键文件/目录 -> "Check Current File"
- 在底部"Checkstyle"面板查看结果

## 三、注意事项

1. 代码格式化快捷键：
    - 单个文件：`Ctrl+Alt+L`
    - 整个项目：`Ctrl+Alt+Shift+L`

2. 提交前检查：
    - 确保格式化所有修改文件
    - 建议运行Checkstyle扫描（可在pre-commit hook中配置）

3. 版本兼容提示：
    - 使用IDEA 2021.3及以上版本
    - Checkstyle插件建议版本：10.12.0+

[配置示例图]（此处可添加关键步骤截图链接）

> 通过上述配置，您的IDEA将完全适配Nacos的代码规范要求，建议将代码风格文件加入项目根目录方便团队共享。