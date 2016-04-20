package com.woods.example.service;


import com.woods.example.common.utils.PageHelper.*;
import com.woods.example.model.User;

import java.util.List;

/**
 * Created by C on 2015/9/24.
 */
public interface IUserService {

    Page<User> findByPage(User user, int pageNumber, int pageSize);

    List<User> findUserAll();

    List<User> getByUser(User user);

    List<User> findByUser(User user);

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
