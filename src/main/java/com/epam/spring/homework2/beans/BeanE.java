package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE implements BeanInitializable, BeanDestroyable {
    private String name;
    private int value;

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


    @Override
    @PreDestroy
    public void beanDestroy() {
        System.out.println(this.getClass().getSimpleName() + " was destroy in beanDestroy method");
    }

    @Override
    @PostConstruct
    public void beanInit() {
        System.out.println(this.getClass().getSimpleName() + " was init in beanInit method");
    }
}
