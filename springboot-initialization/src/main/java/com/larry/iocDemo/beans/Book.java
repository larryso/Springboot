package com.larry.iocDemo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String bookTitle;
    private String bookType;
    private BookPublisher bookPublsher;
}
