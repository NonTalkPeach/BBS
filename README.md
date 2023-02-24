# BBS项目
## 一个开源社区系统
追求一个功能强大，高可用、高并发、高可扩展的分布式开源社区系统
### 功能 

- 用户注册、登录。

- 用户发表博客，评论别人博客、点赞博客。

- 上传\下载社区资源，资源分级处理 （下载上传速度优化） 使用NIO零拷贝优化

-  用户间聊天，相互关注 （未开工）

  #### 后台管理：

  - 管理员管理,强制删除评论，文章 （未开工）
  - 管理用户，禁用、恢复用户 账号 （未开工）
  - 公布消息通知 （未开工）
  
  

### 技术栈：

​	前端：

- bootstrapUI  + 模板引擎themeleaf

​    后端:

- Java语言开发 
- 使用springboot框架，数据库持久层使用mybatis
- 数据库使用mysql，redis
- 聊天室使用websocket
- 基于spring boot、spring cloud的分布式系统

（学习下JVM原理及调优、MQ消息队列原理及使用）

### 开发思考
    1、能否编写出一个统一的基础层框架 例如DAO层，基础层都是增删改查。
### 架构
    redis : 
        1、用于注册模块，暂存注册信息，防止大量无效用户访问MySQL