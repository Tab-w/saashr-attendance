package com.fesco.saashr.web.controller;

import com.fesco.saashr.web.model.User;
import com.fesco.saashr.web.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@RestController
@RequestMapping("/user")
@Api(value = "User的Controller")
public class UserController extends BaseController {
    @Autowired
    private UserServiceImpl service;

    @ApiOperation(value = "查询单条用户信息", notes = "根据id来指定查询用户信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(int id) {
        return service.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查询全部用户信息", notes = "查询全部用户信息")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    public List<User> findList() {
        return null;
    }


    @ApiOperation(value = "创建用户信息", notes = "根据User对象创建用户信息")
    @ApiImplicitParam(name = "user", value = "User实体", dataType = "User")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(User user) {
        return service.insertSelective(user);
    }

    @ApiOperation(value = "删除用户信息", notes = "根据id来指定删除用户信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public int delete(@RequestParam int id) {
        return service.deleteByPrimaryKey(id);
    }

    @ApiOperation(value = "更新用户信息", notes = "根据id来指定更新用户信息")
    @ApiImplicitParam(name = "user", value = "User实体", dataType = "User")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(User user) {
        return service.updateByPrimaryKeySelective(user);
    }

    @ApiIgnore
    @RequestMapping(value = "/findAllPaged/{pageNum}", method = RequestMethod.GET)
    public List<User> findListPaged(@PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return null;
    }
}