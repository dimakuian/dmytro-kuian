package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@Import(SecondBeanConfig.class)
@PropertySource("classpath:application.properties")
public class FirstBeanConfig {

  @Autowired
  private Environment env;

  @Bean
  public BeanA beanA() {
    return new BeanA();
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  @DependsOn({"beanC", "beanD"})
  public BeanB beanB() {
    return new BeanB(env.getProperty("beanB.name"),
        Integer.parseInt(env.getProperty("beanB.value")));
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  @DependsOn("beanD")
  public BeanC beanC() {
    return new BeanC(env.getProperty("beanC.name"),
        Integer.parseInt(env.getProperty("beanC.value")));
  }

  @Bean(initMethod = "beanInit", destroyMethod = "beanDestroy")
  public BeanD beanD() {
    return new BeanD(env.getProperty("beanD.name"),
        Integer.parseInt(env.getProperty("beanD.value")));
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
