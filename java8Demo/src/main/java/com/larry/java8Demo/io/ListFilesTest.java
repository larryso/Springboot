package com.larry.java8Demo.io;

import java.util.List;

public class ListFilesTest {
    public static void main(String[] args) throws Exception {
        String path = "E:\\larry\\temp\\project1";
        List<String> files = FileOperator.fileList(path);
        files.forEach(e->System.out.println(e));
        System.out.println("------------------- fileList End--------------");
        files = FileOperator.directoryStreamList(path);
        files.forEach(e->System.out.println(e));
        System.out.println("------------------- directoryStreamList End--------------");
    }
}
