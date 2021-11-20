# BBS项目
## 一个开源社区系统
追求一个功能强大，高可用、高并发、高可扩展的开源社区系统
### 功能 

- 用户注册、登录。

- 用户发表博客，评论别人博客、点赞博客。

- 上传\下载社区资源，资源分级处理 （下载上传速度优化）

-  用户间聊天，相互关注

  #### 后台管理：

  - 管理员管理文章发表，强制删除评论，文章
  - 管理用户，禁用、恢复用户 账号
  - 公布消息通知
  
  

### 技术栈：

​	前端：

- bootstrapUI  + 模板引擎themeleaf

​    后端:

- Java语言开发 
- 使用springboot框架，数据库持久层使用mybatis
- 数据库使用mysql，redis
- 使用nio多路复用、多线程方法优化上传下载 速度
- 聊天室使用websocket

（JVM原理及调优、MQ消息队列原理及使用、mangodb，，Hadoop）
