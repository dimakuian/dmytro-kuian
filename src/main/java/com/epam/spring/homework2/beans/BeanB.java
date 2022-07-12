package com.epam.spring.homework2.beans;

public class BeanB extends AbstractBean {

  public BeanB(String name, int value) {
    super(name, value);
  }

  public BeanB() {
  }

  public void secondBeanInit() {
    System.out.println(this.getClass().getSimpleName() + " was init in secondBeanInit method");
  }
}
