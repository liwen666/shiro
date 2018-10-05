## 概述

权限体系在现代任何IT系统中都是很基础但又非常重要的部分，无论是传统MIS系统还是互联网系统，出于保护业务数据和应用自身的安全，都会设计自己的授权鉴权策略。

最近项目中也需要用到权限验证功能，项目为spring-boot工程，现成的权限验证框架有shiro和spring-security，shiro相对spring-security来说学习难度要低一点，也是老牌成熟的产品，因此选择shiro作为项目的权限验证框架。

## shiro介绍

Shiro是一个强大易用的Java安全框架,提供了认证、授权、加密和会话管理等功能。

![image](https://github.com/clj198606061111/image/blob/master/20181006/ShiroFeatures.png?raw=true)

**Authentication**：身份认证/登录，验证用户是不是拥有相应的身份；

**Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；

**Session Manager**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；

**Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

**Web Support**：Web支持，可以非常容易的集成到Web环境；

**Caching**：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；

**Concurrency**：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；

**Testing**：提供测试支持；

**Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

**Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

**记住一点，Shiro不会去维护用户、维护权限；这些需要我们自己去设计/提供；然后通过相应的接口注入给Shiro即可。**

### shiro鉴权过程

首先，我们从外部来看Shiro吧，即从应用程序角度的来观察如何使用Shiro完成工作。如下图：

![image](https://github.com/clj198606061111/image/blob/master/20181006/shiro_authentication_process.png?raw=true)

可以看到：应用代码直接交互的对象是Subject，也就是说Shiro的对外API核心就是Subject；其每个API的含义：

**Subject**：主体，代表了当前“用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫，机器人等；即一个抽象概念；所有Subject都绑定到SecurityManager，与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一个门面；SecurityManager才是实际的执行者；

**SecurityManager**：安全管理器；即所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；可以看出它是Shiro的核心，它负责与后边介绍的其他组件进行交互，如果学习过SpringMVC，你可以把它看成DispatcherServlet前端控制器；

**Realm**：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。

### shiro架构

![image](https://github.com/clj198606061111/image/blob/master/20181006/shiro_structure.png?raw=true)

**Subject**：主体，可以看到主体可以是任何可以与应用交互的“用户”；

**SecurityManager**：相当于SpringMVC中的DispatcherServlet或者Struts2中的FilterDispatcher；是Shiro的心脏；所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。

**Authenticator**：认证器，负责主体认证的，这是一个扩展点，如果用户觉得Shiro默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；

**Authrizer**：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；

**Realm**：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；

**SessionManager**：如果写过Servlet就应该知道Session的概念，Session呢需要有人去管理它的生命周期，这个组件就是SessionManager；而Shiro并不仅仅可以用在Web环境，也可以用在如普通的JavaSE环境、EJB等环境；所有呢，Shiro就抽象了一个自己的Session来管理主体与应用之间交互的数据；这样的话，比如我们在Web环境用，刚开始是一台Web服务器；接着又上了台EJB服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到Memcached服务器）；

**SessionDAO**：DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；另外SessionDAO中可以使用Cache进行缓存，以提高性能；

**CacheManager**：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能

**Cryptography**：密码模块，Shiro提高了一些常见的加密组件用于如密码加密/解密的。

## spring-boot-shiro-demo

本demo工程为maven工程项目，工程技术架构采用spring-boot + mybatis + mysql 。


### 数据库设计

![image](https://github.com/clj198606061111/image/blob/master/20181006/auth_permission.jpg?raw=true)



### 工程结构

![image](https://github.com/clj198606061111/image/blob/master/20181006/spring-boot-shiro-demo-project-structure.png?raw=true)

**包说明**

- **common** ：配置类、枚举、异常、工具类等。
- **controller** ：控制层，页面实际请求入口。鉴权注解加在这一层。
- **mapper**：存放mybatis接口类
- **model** : 存放数据库实体类
- **service** ：服务层，在这一层编写具体业务逻辑代码。
- **vo** ：前后端交互实体类。
- **ItcljApplication** ：spring-boot 启动类。

### 核心代码说明

由于工作实际项目需求是接口鉴权，票据采用token，所以采用shiro的token验证方式，shiro默认token鉴权的时候token是从cokies里面取，工作实际项目中token是放在一个自定义http header里面，所以重写了shiro session manager，以使token和shiro session建立映射关系，这个映射关系本demo是保存在redis集群中。

![image](https://github.com/clj198606061111/image/blob/master/20181006/shiro_config_class.png?raw=true)

- **ShiroConfiguration** ：shiro配置类型，实例化Realm、SecurityManager、SessionManager等
- **AjaxPermissionsAuthorizationFilter**：自定义ajax鉴权过滤器，所有返回都采用JSON格式。
- **ShiroSessionManager**：自定义session manager，以使自定义token能够和shiro session绑定，这个映射关系存放在redis集群中。
- **UserRealm**：用户域，从mysql数据库获取用户权限，并显示设置给shiro。


**MainController**

```
 /**
     * 登录
     *
     * shiro登录，shiro采用Facade模式（门面模式），所有与shiro的交互都通过Subject对象API。
     * 调用Subject.login后会触发UserRealm的doGetAuthenticationInfo方法，进行具体的登录验证处理。
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/login")
    public ResponseData login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);//会触发com.itclj.common.shiro.UserRealm的doGetAuthenticationInfo方法
            Session session = SecurityUtils.getSubject().getSession();
            UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_INFO);
            jedisCluster.setex(Constants.REDIS_KEY_PREFIX_SHIRO_TOKEN + userVO.getToken(),
                    Constants.REDIS_SHIRO_TOKEN_EXPIRES,
                    session.getId().toString());
            return new ResponseData(userVO);
        } catch (AuthenticationException e) {
            return new ResponseData("登录失败");
        }
    }
```

**SysUserController**

```
    /**
     * 获取用户详细
     *
     * @param userid 用户ID
     * @return
     */
    @GetMapping("/{userid}")
    @RequiresPermissions("user:detail")//具有 user:detail 权限的用户才能访问此方法
    public ResponseData detail(@PathVariable("userid") Integer userid) {
        return new ResponseData(sysUserService.detail(userid));
    }
```

----
参考
- https://github.com/Heeexy/SpringBoot-Shiro-Vue
- 【Shiro】https://www.cnblogs.com/maofa/p/6407102.html
- 【默认Shiro Session认证方式解析】
https://blog.csdn.net/u011687186/article/details/80702912