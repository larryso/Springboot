package com.larry.iocDemo;

import com.larry.iocDemo.beans.Book;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class DemoApplication {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        Book book = (Book) beanFactory.getBean("book");
        System.out.println(book.getBookPublsher());

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Book book2 = (Book) context.getBean("book");
        System.out.println(book2);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //annotationConfigApplicationContext.register(BeanConfiguration.class);
        annotationConfigApplicationContext.refresh();

        Book book3 = (Book) annotationConfigApplicationContext.getBean("book");
        System.out.println(book3);

    }
}
