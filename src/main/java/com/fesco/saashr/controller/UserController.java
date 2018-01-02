package com.fesco.saashr.controller;

import com.fesco.saashr.model.User;
import com.fesco.saashr.service.impl.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@RestController
@RequestMapping("/user")
@Api(value = "User的Controller")
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation(value = "创建用户信息", notes = "根据User对象创建用户信息")
    @ApiImplicitParam(name = "user", value = "User实体", required = true, dataType = "User")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(User user) {
        return service.insert(user);
    }

    @ApiOperation(value = "删除用户信息", notes = "根据url的id来指定删除用户信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public int deleteById(int id) {
        return service.deleteById(id);
    }

    @ApiOperation(value = "更新用户信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParam(name = "user", value = "User实体", required = true, dataType = "User")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(User user) {
        return service.update(user);
    }

    @ApiOperation(value = "查询单条用户信息", notes = "根据url的id来指定查询用户信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public User findById(int id) {
        return service.findById(id);
    }

    @ApiOperation(value = "查询全部用户信息", notes = "查询全部用户信息")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        return service.findAll();
    }

    @ApiIgnore
    @RequestMapping(value = "/findAllPaged/{pageNum}", method = RequestMethod.GET)
    public List<User> findAll(@PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return service.findAll();
    }
}