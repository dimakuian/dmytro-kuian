package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(SecondBeanConfig.class)
@PropertySource("classpath:application.properties")
public class FirstBeanConfig {

  @Bean
  public BeanA beanA() {
    return new BeanA();
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  @DependsOn({"beanC", "beanD"})
  public BeanB beanB(@Value("${beanB.name}") final String name,
      @Value("${beanB.value}") final int value) {
    return new BeanB(name, value);
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  @DependsOn("beanD")
  public BeanC beanC(@Value("${beanC.name}") final String name,
      @Value("${beanC.value}") final int value) {
    return new BeanC(name, value);
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  public BeanD beanD(@Value("${beanD.name}") final String name,
      @Value("${beanD.value}") final int value) {
    return new BeanD(name, value);
  }

  @Bean
  public BeanE beanE() {
    return new BeanE();
  }

  @Bean
  @Lazy
  public BeanF beanF() {
    return new BeanF();
  }
}
