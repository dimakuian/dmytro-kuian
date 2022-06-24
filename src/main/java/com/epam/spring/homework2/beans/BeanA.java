package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA extends AbstractBean implements InitializingBean, DisposableBean {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void destroy() {
        System.out.println(this.getClass().getSimpleName() + " was destroy with destroy method");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(this.getClass().getSimpleName() + " was initialize with afterPropertiesSet method");
    }
}
