package com.fesco.saashr.web.controller;

import com.fesco.saashr.web.util.DateEditor;
import com.sun.beans.editors.DoubleEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.Date;

/**
 * @author WangXingYu
 * @date 2018-01-05
 */
@RestController
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected ServletContext application;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    protected void initDate(WebDataBinder binder) {
        // 注册格式编辑器以绑定相应的数据类型
        binder.registerCustomEditor(Date.class, new DateEditor("yyyy-MM-dd", true));
        binder.registerCustomEditor(Double.class, new DoubleEditor());
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        // 异常后的操作。。。。。。
        logger.error(e.getMessage());
        return "redirect:/500";
    }
}