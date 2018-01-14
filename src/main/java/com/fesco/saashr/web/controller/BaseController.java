package com.fesco.saashr.web.controller;

import com.fesco.saashr.core.util.DateEditor;
import com.fesco.saashr.core.util.DoubleEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author WangXingYu
 * @date 2018-01-05
 */
@RestController
@ApiIgnore
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;

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
        return "redirect:/500";
    }

    @RequestMapping("/api")
    public ModelAndView api() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}