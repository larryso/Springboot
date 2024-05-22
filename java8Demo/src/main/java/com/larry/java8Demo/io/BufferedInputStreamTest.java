package com.larry.java8Demo.io;

import javax.imageio.stream.ImageInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

public class BufferedInputStreamTest {
    public String upload(ProcessStorage processStorage) throws IOException {
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get(processStorage.getPath())))){
            final int DEFAULT_CHUNK_SIZE = 1024 * 1024;
            byte[] buffer = new byte[DEFAULT_CHUNK_SIZE];
            int chunkSize;
            for(int chunkNumber = 1; (chunkSize = bis.read(buffer)) > 0; chunkNumber ++){
                System.out.println("chunkNumber: "+ chunkNumber + "; chunkSize: "+ chunkSize);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
                byteArrayInputStream.close();
            }
        }
        return "SUCCESS";

    }

    public static void main(String[] args) throws IOException {
        ProcessStorage processStorage = ProcessStorage.builder().path("F:\\xp_work\\01风险分级管控模块(1)(1).rar").build();
        BufferedInputStreamTest test = new BufferedInputStreamTest();
        test.upload(processStorage);
    }
}
