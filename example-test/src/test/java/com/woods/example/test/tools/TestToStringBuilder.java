package com.woods.example.test.tools;

import com.woods.example.model.User;
import org.junit.Test;

/**
 * Created by lin on 2016/3/25.
 */
public class TestToStringBuilder {

    @Test
    public void test(){
        User u = new User();
        u.setName("cc");
        u.setAge(2);
        System.out.println(u.toString());
    }
}
