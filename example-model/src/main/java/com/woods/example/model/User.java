package com.woods.example.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 用户表
 */
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

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


    @Override
    public String toString() {
//        System.out.println("User [id=" + id + ", name=" + name + ", age=" + age + "]");
//        return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE );
    }
}