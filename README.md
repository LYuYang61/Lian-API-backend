# Lian API
## 项目介绍

基于 React + Spring Boot + Dubbo + Gateway 的 API 接口开放调用平台。管理员可以接入并发布接口，可视化各种接口调用情况；用户可以开通接口调用权限、浏览接口及在线调试，并通过客户端 SDK 轻松调用接口。
- 项目前端地址
  https://github.com/LYuYang61/Lian-API-frontend



## 项目展示


- 首页

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/0945bf27-3ede-498d-b81c-6fa12d999ea1)


- 接口详情

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/008249ef-3ec7-4e5d-8228-4e67f7a8995a)


- 接口购买

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/bca3d833-8ce4-4390-b910-6a766cc6d146)


- 接口支付

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/fd269245-0abc-443d-96f2-e0cdbb9b8e87)


- 接口管理

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/5fa10e27-4c96-49fe-9c8c-818944409058)


- 接口分析

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/71af2746-dd8f-4b7a-8fa9-4d63b36ab518)


- 用户管理

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/0c4f5e7a-b97e-4a40-aef6-73b4689de41a)


- 个人中心

![image](https://github.com/LYuYang61/Lian-API-backend/assets/131588563/46184fea-8b8f-4f9d-9b73-9c5e7639acac)





## 系统架构




## 技术选型

### 前端技术栈

- 开发框架：React、Umi
- 脚手架：Ant Design Pro
- 组件库：Ant Design、Ant Design Components
- 语法扩展：TypeScript、Less
- 打包工具：Webpack
- 代码规范：ESLint、StyleLint、Prettier



### 后端技术栈

- 主语言：Java
- 框架：SpringBoot 2.7.0、Mybatis-plus、Spring Cloud
- 数据库：Mysql8.0、Redis
- 中间件：RabbitMq
- 注册中心：Nacos 2.1.0
- 服务调用：Dubbo 3.0.9
- 网关：Spring Cloud Gateway
- 负载均衡：Spring cloud Loadbalancer



## 项目模块

- api-frontend ：项目前端
- api-common ：公共封装类（如公共实体、公共常量，统一响应实体，统一异常处理）
- api-backend ：接口管理平台，主要包括用户、接口相关的功能
- api-gateway ：网关服务，**涉及到网关限流，统一鉴权，统一日志处理，接口统计，接口数据一致性处理**
- api-order ：订单服务，主要涉及到接口的购买等
- api-third-party：第三方服务，主要涉及到腾讯云短信、支付宝沙箱支付功能
- api-interface：为接口服务，提供可供调用的接口
- api-sdk：提供给开发者的SDK





## 功能模块


- 用户、管理员
  - 登录注册：使用令牌桶算法实现手机短信(邮箱)接口的限流，保护下游服务
  - 个人主页，包括上传头像，显示密钥，重新生成ak,sk
  - 管理员：用户管理
  - 管理员：接口管理
  - 管理员：接口分析、订单分析
- 接口
  - 浏览接口信息
  - 数字签名校验接口调用权限
  - SDK调用接口
  - 接口搜索 
  - 购买接口
  - 下载SDK
  - 用户上传自己的接口
- 订单
  - 创建订单
  - 订单超时回滚
  - 支付宝沙箱支付


## 快速上手

### 后端

1. 将各模块配置修改成你自己本地的端口、账号、密码
2. 启动Nacos、Mysql、Redis、RabbitMq
3. 将公共服务 api-common 以及客户端 SDK 安装到本地仓库
4. 按顺序启动服务

服务启动顺序参考：
1. api-backend
2. api-order
3. api-gateway
4. api-third-party
5. api-interface

### 前端

环境要求：Node.js >= 16

安装依赖：

```
yarn
```

启动：

```
npm run start:dev
```
**注意：如果想要体验订单和支付业务并且没有个人云服务器的，需要配置内网穿透才能体验(非必要)**







