# 网关

## 认识
### 网关技术选型
- zuul
- nginx + lua
- kong

### 网关设计功能
- 限流：微服务流量计算，基于流量计算分析限流，可以定义多种限流规则
- 缓存：数据缓存
- 日志：日志记录
- 监控：记录请求响应数据，api耗时，性能监控
- 鉴权：权限身份认证
- 灰度：线上灰度发布，减小风险
- 路由：请求转发

### 网关实现方案
- java + groovy，网关不需要重启就可以动态添加filter
- 网关基础组件：netflix zuul
- 服务注册中心：eureka
- 权限校验：jwt
- api监控：springboot admin
- api日志收集：logback + elk
- 压力测试：jmeter



    