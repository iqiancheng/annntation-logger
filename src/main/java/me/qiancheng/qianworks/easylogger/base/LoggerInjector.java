package me.qiancheng.qianworks.easylogger.base;

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