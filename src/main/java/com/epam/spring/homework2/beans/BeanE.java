package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends AbstractBean implements BeanInitializable, BeanDestroyable {

  public BeanE(String name, int value) {
    super(name, value);
  }

  public BeanE() {
  }

  @Override
  @PreDestroy
  public void beanDestroy() {
    super.beanDestroy();
  }

  @Override
  @PostConstruct
  public void beanInit() {
    super.beanInit();
  }
}
