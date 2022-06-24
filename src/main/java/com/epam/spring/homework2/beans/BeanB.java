package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;

public class BeanB implements BeanInitializable,BeanDestroyable{

    @Value("${beanB.name}")
    private String name;
    @Value("${beanB.value}")
    private int value;

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void beanDestroy() {
        System.out.println(this.getClass().getSimpleName() + " was destroy in beanDestroy method");
    }

    @Override
    public void beanInit() {
        System.out.println(this.getClass().getSimpleName() + " was init in beanInit method");
    }
}
