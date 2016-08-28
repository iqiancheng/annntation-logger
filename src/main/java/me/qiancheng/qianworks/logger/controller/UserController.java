package me.qiancheng.qianworks.logger.controller;

import me.qiancheng.qianworks.logger.base.EnableLog;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="i@qiancheng.me">千橙</a>
 */
@RestController
public class UserController {

    @EnableLog
    private static Logger LOG ;

    @RequestMapping(value = "/test")
    public void test(@RequestParam(value = "name",required = false)String name
            ,@RequestParam(value = "pw",required = false)String pw) {
        // TODO: 8/28/16
    }
}
