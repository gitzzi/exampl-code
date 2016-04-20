package com.woods.example.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private String[] names;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    @Override
    public String toString() {
//        System.out.println("User [id=" + id + ", name=" + name + ", age=" + age + "]");
//        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE );
    }
}