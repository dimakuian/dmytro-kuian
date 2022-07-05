package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.AbstractBean;
import com.epam.spring.homework2.config.FirstBeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FirstBeanConfig.class);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        Arrays.stream(context.getBeanDefinitionNames()).filter(name -> context.getBean(name) instanceof AbstractBean)
                .forEach(name -> System.out.println(context.getBean(name)));

        context.close();
    }

}
