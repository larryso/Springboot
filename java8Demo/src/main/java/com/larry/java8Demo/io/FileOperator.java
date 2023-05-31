package com.larry.java8Demo.io;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOperator {
    /** defined buffer size as 100 bytes**/
    private final static int BUFFER_SIZE=100;

    public static void fileInputStreamCopy(File src, File dest){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(dest);
            byte[] buffer = new byte[BUFFER_SIZE];
            int copySize;
            while((copySize = fileInputStream.read(buffer))>0){
                fileOutputStream.write(buffer, 0, copySize);
                fileOutputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(fileInputStream != null)
                    fileInputStream.close();
                if(fileOutputStream != null)
                    fileOutputStream.close();
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }

    public static void bufferedCopy(File src, File dest) throws IOException {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[BUFFER_SIZE];
            int copySize;
            while((copySize=bufferedInputStream.read(buffer))>0){
                bufferedOutputStream.write(buffer,0,copySize);
            }
        }catch (FileNotFoundException e){

        }finally {
            if(bufferedInputStream != null)
                bufferedInputStream.close();
            if(bufferedOutputStream != null)
                bufferedOutputStream.close();
        }
    }
    public List<String> fileWalk(String path){
        return null;
    }
    public List<String> fileWalkWithExtension(String path, String extension){
        return null;
    }
    public static List<String> fileList(String path){
        try(Stream<Path> stream = Files.list(Paths.get(path))){
            return stream.
                    filter(Files::isRegularFile).
                    map(file -> file.toAbsolutePath().toString()).
                    collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<String> directoryStreamList(String path) throws Exception{
        List<String> fileList = new ArrayList<>();
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path))){
            stream.forEach(e -> fileList.add(e.toAbsolutePath().toString()));
        }
        return fileList;
    }
}
