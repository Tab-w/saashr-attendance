package com.fesco.saashr.web.controller;

import com.fesco.saashr.core.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @RequestMapping
    @Log(type = "请求操作", description = "请求主页")
    public String index() {
        return "index";
    }

    @RequestMapping("/testLog")
    public void testLog() {
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }
}