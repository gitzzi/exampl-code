package com.woods.example.service.impl;

import com.woods.example.common.utils.PageHelper;
import com.woods.example.dao.IUserDao;
import com.woods.example.service.IUserService;
import com.woods.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by C on 2015/9/24.
 */

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public PageHelper.Page<User> findByPage(User user, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList = userDao.selectByPage(user);
        return PageHelper.endPage();
    }

    @Override
    public List<User> findByUser(User user) {
        return userDao.selectByPage(user);
    }

    @Override
    public List<User> findUserAll() {
        return userDao.selectAll();
    }

    @Override
    public List<User> getByUser(User user) {
        return userDao.selectByUser(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int update(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    /**
     * 保存
     *
     * @param user
     * @return
     */
    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

}
