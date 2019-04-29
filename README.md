
### 技术选型

#### 后端技术

技术 | 说明 | 个人学习文章地址
----|----|----
Spring Boot | 容器+MVC框架 | 
Spring Cloud | 微服务框架 | 
Spring Cloud Gateway | 网关 |
Spring Cloud Config | 配置中心 |
Spring Security | 认证和授权框架 | 
MyBatis-Plus | ORM框架（包含分页等）  | 
Swagger-UI | 文档生产工具 | 
Hibernator-Validator | 验证框架 | 
Elasticsearch | 搜索引擎 | 
RabbitMq | 消息队列 | 
Redis | 分布式缓存 | 
FastDFS| 图片存储服务器 | 
Docker | 应用容器引擎 | 
Druid | 数据库连接池 | 
JWT | JWT登录支持 | 
LogStash | 日志收集 | 
Lombok | 简化对象封装工具 | 

### 第三方接口

工具 |  说明 |  下载
----|----|----
阿里短信 | 短信发送
阿里支付 | 支付宝支付
微信支付 | 微信支付
邮箱？ | 邮件发送

### 微服务端口

服务名 | 端口 | 说明 
----|----|----
spring-cloud-eureka | 7000 | 注册中心服务器
spring-cloud-gateway | 7001 | 网关地址
image-service（待修改） | 7005 | 图片微服务
item-service | 7010 | 商品微服务
user-service | 7011 | 用户微服务
search-service | 7013 | 搜索微服务
goods-web-service | 7102 | 商品详情页展示页面(前端展示)


### 前端端口

服务名 | 端口 | 说明 
----|----|----
index-web | 7100 | 商城主页
manager-web | 7101 | 后台管理页面






### 开发环境

工具 | 版本号 | 下载（没有地址的就是采用docker安装:https://hub.docker.com/）
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 8.0 | 
Redis | 5.0.4 | 
Elasticsearch | 6.6.1 | 
Kibana | 6.6.1 | https://www.elastic.co/cn/downloads/past-releases/
RabbitMq | 3.7.14 | 
nginx | 1.15 | http://nginx.org/en/download.html
FastDFS | season/fastdfs | 

### 开发工具

工具 |  说明 |  下载
----|----|----
IDEA | 开发工具
SwitchHosts | 本地host管理
RedisDesktop | redis图形化界面
X-Shell | linux连接工具
Navicat | 数据库图形化界面
