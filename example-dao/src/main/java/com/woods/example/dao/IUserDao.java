package com.woods.example.dao;

import com.woods.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lin on 2016/3/17.
 */
public interface IUserDao {

    List<User> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
