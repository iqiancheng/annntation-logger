# easy-logger
make logger easier for using.

- [简化java开发中的日志引入［注解方式］](http://qiancheng.me/coding/logger-injection-with-annotation.md)

## Problem #1
在实际开发中，我们经常要在Service或者Controller中调用一个日志类，每次都得调用LoggerFactory.getLogger( )方法

```Java
@RestController
public class UserController {
	 /** 获取日志实例 */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
```
## Sloution
为什么不能把这个繁琐的日志实例变得可以直接复制，不用修改任何东西呢？我们可以用注解和反射来做。比如首先我们定义一个注解，假设就叫做`EnableLog`吧。

```java
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;  
  
import java.lang.annotation.Documented;  
import java.lang.annotation.Retention;  
import java.lang.annotation.Target;  
  
@Retention(RUNTIME)  
@Target(FIELD)  
@Documented  
public @interface EnableLog {
}
```

在Spring管理的bean初始化放入容器之前，对引入`EnableLog`注解标记的位置注入logger实例。

```java
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;
import java.util.logging.Logger;

/**
 *  日志注入
 * @author <a href="i@qiancheng.me">千橙</a>
 */
@Component
public class LoggerInjector implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    /** bean 被注册到容器之前的处理 */
    public Object postProcessBeforeInitialization(final Object bean,
                                                  String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException,
                    IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                if (field.getAnnotation(EnableLog.class) != null) {

                    Class clazz = bean.getClass();
                    if("org.slf4j.Logger".equals(field.getType().getName())){
                        field.set(bean, LoggerFactory.getLogger(clazz));
                    }else if("java.util.logging.Logger".equals(field.getType().getName())){
                        field.set(bean, Logger.getLogger(clazz.getName()));
                    }
                }
            }
        });
        return bean;
    }
}
```

## Usage
可以在任意Service和Controller中获取一个logger实例，只需要在成员属性上加一个`@EnableLog` 注解，可以随意复制，不需要再去改getLogger(xx.class)中的参数啦～～
 
```java
@EnableLog
private static Logger LOG ;
```    

## Source Code
<https://github.com/iqiancheng/easy-logger>
<https://github.com/iqiancheng/easy-logger/releases/tag/v1.0>


