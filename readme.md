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
2. 对应的mapper.xml文件开启二级缓存<cache />
3. 对应的pojo类进行序列化，public class User implements Serializable，因为二级缓存数据存储介质多种多样，不一样在内存
4. 需要SqlSession执行关闭操作,将SqlSession中的数据写到二级缓存区域
5. SqlSession执行commit操作后，清空缓存
6. 在statement中设置useCache=false，禁用当前二级缓存，默认是true开启
7. 在statement中设置flushCache=false，禁用当前二级缓存刷新，默认是true开启，如果改为false则不会刷新，使用缓存时如果手动修改数据库的数据后，再次查询会出现脏读
