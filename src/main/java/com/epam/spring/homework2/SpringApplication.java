package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.AbstractBean;
import com.epam.spring.homework2.config.FirstBeanConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FirstBeanConfig.class);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        for (String name : context.getBeanDefinitionNames()) {
            if (context.getBean(name) instanceof AbstractBean) {
                System.out.println(context.getBean(name));
            }
        }

        context.close();
    }
}
