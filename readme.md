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