ExampleCode实例代码

模块：包含7个子模块
example-model
example-dao
example-service
example-web
example-common
example-utile
example-test


MyBatis使用总结：http://note.youdao.com/share/?id=4318026d736a9ba67c223910b0c3ee8d&type=note

example-model 实体类模块

1.所有实体的toString()方法 都用"+"的方式拼接，不但写着繁琐，还会创建许多string对象。
    可以使用：（commons-lang.jar）ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE );


example-test 测试模块

    关于Class.getResource和ClassLoader.getResource的路径问题（http://swiftlet.net/archives/868）
    GetFilePath
    
    关于poi导出excel
        com.woods.example.test.exportexcel

    关于jackrabbit
        com.woods.example.test.other.jackrabbit