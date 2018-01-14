package com.fesco.saashr.web.service.impl;

import com.fesco.saashr.web.dao.UserMapper;
import com.fesco.saashr.web.model.User;
import com.fesco.saashr.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@Service
public class UserServiceImpl implements BaseService<User> {
    @Autowired
    private UserMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return mapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }
}