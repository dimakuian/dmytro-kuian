package com.epam.spring.homework2.beans;

import java.util.Random;

public abstract class AbstractBean implements BeanInitializable, BeanDestroyable {

  private String name;
  private int value;

  public AbstractBean(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public AbstractBean() {
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "{" +
        "name='" + name + '\'' +
        ", value=" + value +
        '}';
  }

  public void validate() {
    if (name == null) {
      name = "not null now";
    }
    if (value <= 0) {
      value = new Random().nextInt(Integer.MAX_VALUE);
    }
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
