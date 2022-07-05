package com.epam.spring.homework2.beans;

public class BeanD extends AbstractBean implements BeanInitializable, BeanDestroyable {

  public BeanD(String name, int value) {
    super(name, value);
  }

  public BeanD() {
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
