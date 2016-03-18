package com.woods.example.test.user;

import com.woods.example.model.User;
import com.woods.example.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lin on 2016/3/18.
 */
public class TestUser {

    ApplicationContext context = null;
    IUserService userService = null;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("classpath:test-spring-context-web.xml");
        userService = (IUserService) context.getBean("userService");
    }

    @Test
    public void testFind(){
        User u = userService.findUserById(1);
        u.toString();
    }

    @Test
    public void testSave(){
        User u = new User("小明75", 23);
        userService.save(u);
        u.toString();
    }

    @Test
    public void testUpdate(){
        User oldUser = userService.findUserById(1);
        oldUser.toString();
        oldUser.setName("小非3");
        userService.update(oldUser);
        oldUser.toString();
    }

    @Test
    public void testFindAll(){
        List<User> userList = userService.findUserAll();
        for ( User u : userList){
            u.toString();
        }
    }
}
