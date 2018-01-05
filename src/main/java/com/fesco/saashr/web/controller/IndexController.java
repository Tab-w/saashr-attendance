package com.fesco.saashr.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController {

    @RequestMapping
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