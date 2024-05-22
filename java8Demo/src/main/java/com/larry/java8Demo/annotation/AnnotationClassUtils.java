package com.larry.java8Demo.annotation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationClassUtils {
    public static Set<Object> entitySets = new HashSet<>();

    public static Set<Class<?>> loadAnnotatedClasses(String packageName) throws Exception {
        //Set<Class<?>> classSet =
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//        String line = "";
//        while((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
//        return reader.lines().
//                filter(line ->line.endsWith(".class")).
//                map(line->getClass(line, packageName)).
//                collect(Collectors.toSet());
        return null;
    }

    private static Class getClass(String className, String packageName) {
       try{
           System.out.println(packageName+"."+className.substring(0, className.lastIndexOf(".")));
           return Class.forName(packageName+"."+className.substring(0, className.lastIndexOf(".")));
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
       return null;
    }

//    public static void main(String[] args) throws IOException {
//        loadAnnotatedClasses("com.larry.java8Demo.annotation");
//    }
}
