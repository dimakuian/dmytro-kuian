package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import org.springframework.context.annotation.*;

@Configuration
@Import(SecondBeanConfig.class)
@PropertySource("classpath:application.properties")
public class FirstBeanConfig {

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean(initMethod = "beanInit" , destroyMethod = "beanDestroy")
    @DependsOn({"beanC","beanD"})
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean(initMethod = "beanInit" , destroyMethod = "beanDestroy")
    @DependsOn("beanD")
    public BeanC beanC() {
        return new BeanC();
    }

    @Bean(initMethod = "beanInit" , destroyMethod = "beanDestroy")
    public BeanD beanD() {
        return new BeanD();
    }
}
