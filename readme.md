# java-tests
### java 练习</br>
### 2017-09-27 创建</br>
### 2017-10-01 mybatis总结原始dao开发问题
1. dao接口实现类方法中存在大量模版方法，设想能否将这些代码提取出来，大大减轻程序员的工作量
2. 调用sqlsession方法时将statement的id硬编码了
3. 调用sqlsession方法时传入的变量，由于sqlsession方法使用泛型，即使变量类型传入错误，在编译阶段也不报错，不利于程序员开发
### 2017-10-02 mybatis总结mapperd代理开发问题
####  开发规范
1. 在mapper.xml中namespace等于mapper接口地址
2. mapper.java接口中的方法名和mapper.xml中statement的id一致
3. mapper.java接口中的方法输入参数类型和mapper.xml中statement的parameterType指定的类型一致
4. mapper.java接口中的方法返回值类型和mapper.xml中statement中的resultType指定的类型一致
5. mapper.java和mapper.xml映射文件名称保持一致，且在一个目录中，mapper.xml可自动加载

#### properties特性
1. 在 properties 元素体内定义的属性首先被读取
2. 然后会读取properties 元素中resource或 url 加载的属性，它会覆盖已读取的同名属性
3. 最后读取parameterType传递的属性，它会覆盖已读取的同名属性

### 2017-10-03总结
resultType：
作用：
	将查询结果按照sql列名pojo属性名一致性映射到pojo中。
场合：
	常见一些明细记录的展示，比如用户购买商品明细，将关联查询信息全部展示在页面时，此时可直接使用resultType将每一条记录映射到pojo中，在前端页面遍历list（list中是pojo）即可。

resultMap：
	使用association和collection完成一对一和一对多高级映射（对结果有特殊的映射要求）。

association：
作用：
	将关联查询信息映射到一个pojo对象中。
场合：
	为了方便查询关联信息可以使用association将关联订单信息映射为用户对象的pojo属性中，比如：查询订单及关联用户信息。
	使用resultType无法将查询结果映射到pojo对象的pojo属性中，根据对结果集查询遍历的需要选择使用resultType还是resultMap。
	
collection：
作用：
	将关联查询信息映射到一个list集合中。
场合：
	为了方便查询遍历关联信息可以使用collection将关联信息映射到list集合中，比如：查询用户权限范围模块及模块下的菜单，可使用collection将模块映射到模块list中，将菜单列表映射到模块对象的菜单list属性中，这样的作的目的也是方便对查询结果集进行遍历查询。
	如果使用resultType无法将查询结果映射到list集合中。
	
### 2017-10-05总结
#### 一级缓存和二级缓存
一级缓存mybatis默认开启</br>
二级缓存的总结：1-4为开启步骤</br>
1. SqlMapConfig.xml配置文件中开启二级缓存<setting name="cacheEnabled" value="true"/>
2. 对应的mapper.xml文件开启二级缓存<cache />，属性type表示指定cache接口的实现类的类型，mybatis默认使用PerpetualCache，要和ehcache整合，需要配置type为ehcache实现cache接口的类型
3. 对应的pojo类进行序列化，public class User implements Serializable，因为二级缓存数据存储介质多种多样，不一样在内存
4. 需要SqlSession执行关闭操作,将SqlSession中的数据写到二级缓存区域
5. SqlSession执行commit操作后，清空缓存
6. 在statement中设置useCache=false，禁用当前二级缓存，默认是true开启
7. 在statement中设置flushCache=false，禁用当前二级缓存刷新，默认是true开启，如果改为false则不会刷新，使用缓存时如果手动修改数据库的数据后，再次查询会出现脏读
8. 应用场景：访问多的查询请求且用户对查询结果实时性要求不高
9. 局限性：对细粒度的数据级别的缓存实现不好，因为mybatis的二级缓存区以mapper为单位划分，比如当一个商品信息变化会将所有商品信息的缓存数据清空，解决此类问题需要在业务层根据需求对数据有针对性缓存
#### spring和mybatis整合，原始dao开发
注意：让接口实现类继承SqlSessionDaoSupport，可以自动加载sqlSessionFactory,通过this.getSqlSession()得到sqlSession

### 2017-10-09总结
#### 多个映射器和多个适配器可以共存，如果映射的url在非注解和注解映射器都存在的话，则优先访问url顺序靠前的
#### 非注解处理器映射器和处理器适配器：
#### 前端控制器从spring-webmvc-3.2.0.RELEASE.jar里的org.springframework.web.servlet里的DispatcherServlet.properties文件中加载处理器映射器、适配器、视图解析器等组件，如果不在springmvc.xml中配置,使用默认加载的
1. BeanNameUrlHandlerMapping是简单处理器映射器，将bean的name作为url进行查找
2. SimpleUrlHandlerMapping是BeanNameUrlHandlerMapping映射器的加强版，它可以将url和处理器bean的id进行统一映射配置
3. SimpleControllerHandlerAdapter是非注解处理器适配器，要求编写的Handler实现Controller接口
4. HttpRequestHandlerAdapte是非注解处理器适配器，要求编写的 Handler实现HttpRequestHandler接口，可以使用里面的request参数，设置响应的数据格式，比如响应json格式，这是Controller做不到的

#### 注解处理器映射器和处理器适配器：
#### 如果使用的映射器是注解的话，则适配器也得使用注解的方式开发，它们必须配对使用
#### 如果使用<context:component-scan标签批量加载Handler的时候，springmvc的jar包或者jdk版本过低的话，则会报500错误，解决办法为把springmvc的的jar包替换为4.0版本以上的或者把jdk降到1.7
1. 在spring3.1之前使用org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping注解映射器
2. 在spring3.1之后使用org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping注解映射器
3. 在spring3.1之前使用org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter注解适配器      
4. 在spring3.1之后使用org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter注解适配器

### 2017-10-10总结
#### ssm配置文件的目录
config源代码文件夹里面有：
1. mybatis文件夹：sqlMapConfig.xml

2. spring文件夹</br>
①.applicationContext-dao.xml</br>
②.applicationContext-service.xml</br>
③.applicationContext-transaction.xml</br>
④.springmvc.xml</br>

3. db.properties（数据库配置文件）
4. log4j.properties（日志配置文件）
5. web.xml

#### ssm配置文件的引用
web.xml：
1. 加载spring容器，使用org.springframework.web.context.ContextLoaderListener下载spring目录下applicationContext开头的xml文件，即是加载applicationContext-dao.xml、applicationContext-service.xml、applicationContext-transaction.xml</br>
语法为：赋值contextConfigLocation属性的值为classpath:spring/applicationContext-*.xml
2. 配置springmvc前端控制器，加载的配置文件（配置处理器映射器，处理器适配器等等） 如果不配置contextConfigLocation，默认加载的是/WEB_INF/servlet名称-servlet.xml(springmvc-servlet.xml)</br>
语法为：赋值contextConfigLocation属性的值为classpath:spring/springmvc.xml</br>

applicationContext-dao.xml：
1. 加载mybatis的sqlMapConfig.xml文件，配置sqlSessionFactory时引入</br>
语法为：赋值configLocation属性的值为classpath:mybatis/sqlMapConfig.xml
2. 加载db.properties，在配置数据源时需要用到，语法为：context:property-placeholder location="classpath:db.properties"</br>

log4j.properties在log4j的jar包会自动引入

### 2017-10-11总结
#### redirect和forward的区别
#### 两者都是可以跳转到指定页面
redirect为重定向页面：浏览器地址栏中的url会变化。修改提交的request数据无法传到重定向的地址。因为重定向后重新进行request（request无法共享）
forward为转发页面：浏览器地址栏url不变，request可以共享