package com.fesco.saashr.controller;

import com.fesco.saashr.model.Info;
import com.fesco.saashr.service.impl.InfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: WangXingYu
 * @date: 2017-12-31 10:08
 */
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService service;

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public List<Integer> insert(Info info) {
        List<Integer> list = new ArrayList<>(2000);
//        for (int i = 0; i < 2000; i++) {
//            list.add(service.insert(info));
//        }
        service.insert(info);
        return list;
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    public int deleteById(int id) {
        return service.deleteById(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public int update(Info info) {
        return service.update(info);
    }

    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Info findById(int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<Info> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "findAllPaged/{pageNum}", method = RequestMethod.GET)
    public List<Info> findAll(@PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return service.findAll();
    }
}