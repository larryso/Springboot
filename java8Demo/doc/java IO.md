# Java - Files and I/O

![](../images/file_io.jpg)

## Difference between FileInputStream and BufferedInputStream in java file IO

|BufferedInputStream|FileInputStream|
|---|---|
|BufferedInputStream is buffered.|FileInputStream is not buffered.|
|BufferedInputStream reads bytes from another InputStream (Eg - FileInputStream), <br />So, BufferedInputStream is wrapper formed on FileInputStream.<br /><br />FileInputStream fis = new FileInputStream("c:/myFile.txt");<br />BufferedInputStream bis = new BufferedInputStream(fis);|FileInputStream reads bytes from a file.<br /><br />FileInputStream fis = new FileInputStream("c:/myFile.txt");|
|when BufferedInputStream.read() is called mostly data is read from the buffer. <br /> When data is not available available in buffer a call is made to read system file and lot of bytes are kept in buffer. <br /> BufferedInputStream.readLine() method reads whole line and keep it in buffer.|Every time FileInputStream.read() is called a call is made to read a system file. <br /> FileInputStream.read()
reads 1 byte (8-bit) at a time.|
|A BufferedInputStream enables another input stream to buffer the input and supports the mark and reset methods.<br />An internal buffer array (by default size is 8192 bytes)is created when the BufferedInputStream is created. <br /> As bytes from the stream are read or skipped, the internal buffer is refilled as necessary from the contained input stream, many bytes at a time. |A FileInputStream obtains input bytes from a file in a file system.<br /> And does not supports mark and reset methods.|
|BufferedInputStream is much <b>faster</b> as compared to FileInputStream.|FileInputStream is <b>slower</b> as compared to BufferedInputStream.|

For Example

``` java
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
```

write a test method:

``` java
public class FileIOTest {
    public static void main(String[] args)throws Exception {
        //Test compare FileInputStream and BufferedFileinputStream
        File src = new File("G:\\photo\\我的视频\\七宝.mp4");
        File dest = new File("G:\\photo\\我的视频\\d\\cpoy_七宝.mp4");
        long startTime = System.currentTimeMillis();
        FileOperator.fileInputStreamCopy(src, dest);
        long endTime = System.currentTimeMillis();
        System.out.println("Copy File Using: "+ (endTime-startTime)+"");
        startTime = System.currentTimeMillis();
        FileOperator.bufferedCopy(src, dest);
        endTime = System.currentTimeMillis();
        System.out.println("Copy File Using: "+ (endTime-startTime)+"");
    }


}
```

output:

Copy File Using: 133640
Copy File Using: 6871

 so we can see the bufferedInputStream read is more faster

 ## BufferedInputStream mark() and reset() method

 mark() method id used to mark current position in the inputStream
 reset() method fixes the position at the last mark position so that same byte can be read again 

 ```java
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
 ```
 a,b,mark
c,d,c
d
e
f
g
h
i


