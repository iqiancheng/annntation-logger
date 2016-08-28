package me.qiancheng.qianworks.logger.base;

import com.google.common.base.Joiner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="i@qiancheng.me">千橙</a>
 */
@Aspect
@Component
public class LogAdvice {

    private static final String COMMA = ", ";

    @Pointcut("execution(* me.qiancheng.qianworks.logger.controller.*.*(..))")
    public void pointcut(){ }

    @Around(value="pointcut()")
    public void log(ProceedingJoinPoint point) throws Throwable {
        long time=System.currentTimeMillis();
        point.proceed();
        Class clazz = point.getTarget().getClass();
        String className = clazz.getName();//获取目标类名
        String methodName = point.getSignature().getName();//获取目标方法签名
        Object[] paramArray = point.getArgs();//获取目标方法体参数
        String params = argsToString(paramArray);
        final Logger LOG = getLogger(clazz);
        LOG.info(className+":"+ methodName+" :: "+(System.currentTimeMillis()-time)+"ms.");
        LOG.info("==>params={}",params);
    }

    private String argsToString(Object[] paramArray) {
        return Joiner.on(COMMA).
                appendTo(new StringBuilder(),paramArray).
                toString();
    }

    private final Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

}
