package com.woods.example.test.dao;

import com.woods.example.dao.IUserDao;
import com.woods.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lin on 2016/5/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-spring-context-web.xml"})
public class TestUserDao {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testSelect(){
        User uparam = new User();
        uparam.setName("1");
        List<User> users = userDao.selectByUser(uparam);
        for (User u : users){
            System.out.println(u.toString());
        }
    }

}
