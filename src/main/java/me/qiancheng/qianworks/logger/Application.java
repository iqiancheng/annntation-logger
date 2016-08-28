package me.qiancheng.qianworks.logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *  入口
 * @author <a href="i@qiancheng.me">千橙</a>
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan(basePackages="me.qiancheng.qianworks")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
