package com.ming.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(SpringIocApplication.class, args);
//        MyBeanFactoryImpl beanFactory = new MyBeanFactoryImpl();
//        User user1 = (User) beanFactory.getBeanByName("com.ming.springioc.domain.User");
//        User user2 = (User) beanFactory.getBeanByName("com.ming.springioc.domain.User");
//        Student student1 = user1.getStudent();
//        Student student2 = user1.getStudent();
//        Student student3 = (Student) beanFactory.getBeanByName("com.ming.springioc.domain.Student");
//        System.out.println(user1);
//        System.out.println(user2);
//        System.out.println(student1);
//        System.out.println(student2);
//        System.out.println(student3);
    }

}
