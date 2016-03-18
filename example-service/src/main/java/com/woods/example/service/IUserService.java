package com.woods.example.service;


import com.woods.example.model.User;

import java.util.List;

/**
 * Created by C on 2015/9/24.
 */
public interface IUserService {

    List<User> findUserAll();

    /**
     * 根据用户id查找
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 保存
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);
}
