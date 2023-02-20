package com.larry.java8Demo.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

public class FileIOTest {
    public static String convertByte2String(byte byteValue){
        byte[] bytes = {byteValue};
        return new String(bytes, 0,1, Charset.defaultCharset());
    }
    public static void main(String[] args)throws Exception {
        //Test compare FileInputStream and BufferedFileinputStream
//        File src = new File("G:\\photo\\我的视频\\七宝.mp4");
//        File dest = new File("G:\\photo\\我的视频\\d\\cpoy_七宝.mp4");
//        long startTime = System.currentTimeMillis();
//        FileOperator.fileInputStreamCopy(src, dest);
//        long endTime = System.currentTimeMillis();
//        System.out.println("Copy File Using: "+ (endTime-startTime)+"");
//        startTime = System.currentTimeMillis();
//        FileOperator.bufferedCopy(src, dest);
//        endTime = System.currentTimeMillis();
//        System.out.println("Copy File Using: "+ (endTime-startTime)+"");

        //test mark() method
        byte[] bytes = {'a','b','c','d','e','f','g','h','i'};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        //we use BufferedInputStream to read from byte array
        BufferedInputStream bis = new BufferedInputStream(in);
        //read one byte
        System.out.print(convertByte2String((byte) bis.read())+",");
        //read the second byte
        System.out.print(convertByte2String((byte) bis.read())+",");
        System.out.println("mark");
        //mark position on the third byte
        bis.mark(3);
        //read the third byte
        System.out.print(convertByte2String((byte) bis.read())+",");
        //read the forth byte
        System.out.print(convertByte2String((byte) bis.read())+",");
        //call reset and start read from last mark point
        bis.reset();
        int readCount;
        while((readCount=bis.read())>0){
            System.out.println(convertByte2String((byte) readCount));
        }

    }


}
