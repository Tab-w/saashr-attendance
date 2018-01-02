package com.fesco.saashr.controller;

import com.fesco.saashr.model.User;
import com.fesco.saashr.service.impl.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(User user) {

        return service.insert(user);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public int deleteById(int id) {
        return service.deleteById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(User user) {
        return service.update(user);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public User findById(int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/findAllPaged/{pageNum}", method = RequestMethod.GET)
    public List<User> findAll(@PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return service.findAll();
    }
}