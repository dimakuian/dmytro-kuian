package com.epam.spring.homework2.beans;

import java.util.Random;

public abstract class AbstractBean {

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
      name = "default";
    }
    if (value <= 0) {
      value = new Random().nextInt(Integer.MAX_VALUE);
    }
  }

}
