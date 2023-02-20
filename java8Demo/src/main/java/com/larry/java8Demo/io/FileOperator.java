package com.larry.java8Demo.io;

import java.io.*;

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
}
