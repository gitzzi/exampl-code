package com.woods.example.tools;

import org.junit.Test;

/**
 * Created by lin on 2016/3/21.
 * 获取文件路径
 */
public class GetFilePath {

    /**
     * 获取文件路径
     */
    @Test
    public void testGetFilePath(){
        String path = GetFilePath.class.getClassLoader().getResource("log4j.properties").getPath();
        String path2 = GetFilePath.class.getResource("/log4j.properties").getPath();
        System.out.println(path);
        System.out.println(path2);
    }

    /**
     * path不以’/'开头时，默认是从此类所在的包下取资源
     * path以’/'开头时，则是从ClassPath根下获取
     */
    @Test
    public void testGetResource(){
        System.out.println(GetFilePath.class.getResource(""));
        System.out.println(GetFilePath.class.getResource("/"));
    }

    /**
     * path不能以’/'开头
     * path不以’/'开头时，则是从ClassPath根下获取
     */
    @Test
    public void testGetClassLoaderResource(){
        GetFilePath t = new GetFilePath();
        System.out.println(t.getClass());
        System.out.println(t.getClass().getClassLoader());
        System.out.println(t.getClass().getClassLoader().getResource(""));
        System.out.println(t.getClass().getClassLoader().getResource("/"));//null
    }

}
