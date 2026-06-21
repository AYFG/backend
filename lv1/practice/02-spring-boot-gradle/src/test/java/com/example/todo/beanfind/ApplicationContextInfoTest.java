package com.example.todo.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ApplicationContextInfoTest {

    @Autowired
    ApplicationContext ac;

    @Test
    @DisplayName("Print all bean names in the Spring container")
    void findAllBean() {
        System.out.println("=========== [Bean list start] ===========");

        String[] allBeanNames = ac.getBeanDefinitionNames();

        for (String beanName : allBeanNames) {
            Object bean = ac.getBean(beanName);
            System.out.println("name: " + beanName + " | object: " + bean);
        }

        System.out.println("=========== [Bean list end] ===========");
    }
}