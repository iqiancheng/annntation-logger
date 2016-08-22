package me.qiancheng.qianworks.easylogger.controller;

import me.qiancheng.qianworks.easylogger.base.EnableLog;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iamya on 6/25/2016.
 */
@RestController
public class UserController {

    @EnableLog
    private static Logger LOG ;
    // private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/test")
    public void auth() {
        LOG.debug("::::Test::::");
    }
}
