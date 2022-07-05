package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA extends AbstractBean implements InitializingBean, DisposableBean {

  public BeanA(String name, int value) {
    super(name, value);
  }

  public BeanA() {
  }

  @Override
  public void destroy() {
    System.out.println(this.getClass().getSimpleName() + " was destroy in destroy method");
  }

  @Override
  public void afterPropertiesSet() {
    System.out.println(
        this.getClass().getSimpleName() + " was initialize in afterPropertiesSet method");
  }
}
