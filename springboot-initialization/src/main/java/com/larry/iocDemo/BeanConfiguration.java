//package com.larry.iocDemo;
//
//import com.larry.iocDemo.beans.Book;
//import com.larry.iocDemo.beans.BookPublisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class BeanConfiguration {
//    @Bean(name="book2")
//    public Book getBookBean(){
//        Book book = new Book("Spring IoC Demo", "java",getBookPublisherBean() );
//        return book;
//    }
//    @Bean(name="bookPublisher")
//    public BookPublisher getBookPublisherBean(){
//        BookPublisher bookPublisher = new BookPublisher("102", "Larry", "Song");
//        return bookPublisher;
//    }
//}
