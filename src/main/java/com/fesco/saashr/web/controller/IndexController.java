package com.fesco.saashr.web.controller;

import com.fesco.saashr.core.annotation.ControllerLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@RestController
@ApiIgnore
@RequestMapping("/index")
public class IndexController extends BaseController {
    @RequestMapping
    @ControllerLog(operation = "访问首页")
    public String index() {
        return "index";
    }

    @RequestMapping("/testLog")
    @ControllerLog(operation = "测试Log")
    public void testLog() {
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }
}