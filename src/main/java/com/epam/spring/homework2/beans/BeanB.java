package com.epam.spring.homework2.beans;

public class BeanB extends AbstractBean implements BeanInitializable, BeanDestroyable {

  public BeanB(String name, int value) {
    super(name, value);
  }

  public BeanB() {
  }

  @Override
  public void beanDestroy() {
    System.out.println(this.getClass().getSimpleName() + " was destroy in beanDestroy method");
  }

  @Override
  public void beanInit() {
    System.out.println(this.getClass().getSimpleName() + " was init in beanInit method");
  }

  public void secondBeanInit() {
    System.out.println(this.getClass().getSimpleName() + " was init in secondBeanInit method");
  }
}
