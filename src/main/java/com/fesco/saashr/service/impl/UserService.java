package com.fesco.saashr.service.impl;

import com.fesco.saashr.dao.UserMapper;
import com.fesco.saashr.model.User;
import com.fesco.saashr.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@Service
public class UserService implements BaseService<User> {
    @Autowired
    private UserMapper mapper;

    @Override
    public int insert(User entity) {
        return mapper.insert(entity) > 0 ? entity.getId() : -1;
    }

    @Override
    public int deleteById(int id) {
        return mapper.deleteById(id);
    }

    @Override
    public int update(User entity) {
        return mapper.update(entity);
    }

    @Override
    public User findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }
}
