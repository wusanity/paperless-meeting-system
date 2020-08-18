package com.szsm.videomeeting.base.util;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: huangxin
 * @Date: Created in 上午9:14 2019/1/3
 * @Description:
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置上下文
     *
     * @param applicationContext 上下文
     */
    @Override
    @SneakyThrows
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取运行上下文
     *
     * @return 运行上下文
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名字获取bean
     *
     * @param name beanName
     * @return bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类获取bean
     *
     * @param clazz beanClass
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据名字和类获取bean
     *
     * @param name  beanName
     * @param clazz beanClass
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}