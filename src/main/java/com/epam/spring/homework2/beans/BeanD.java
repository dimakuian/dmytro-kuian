package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;

public class BeanD implements BeanInitializable, BeanDestroyable {

    @Value("${beanD.name}")
    private String name;
    @Value("${beanD.value}")
    private int value;

    @Override
    public String toString() {
        return "BeanA{" +
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
