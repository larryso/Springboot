package com.larry.java8Demo.stream;

import com.larry.java8Demo.io.ProcessStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * stream collect() method:
 * The method accepts a Collector that describes how the elements of the stream should be collected and aggregated as an argument.
 *
 * stream filter() method:
 *
 * Function.identity()
 * method that return its input argument
 */
public class ListToMapTest {
    public static void main(String[] args) {
        List<ProcessStorage> processStorageList = new ArrayList<>();
        for(int i=0;i< 10; i++){
            ProcessStorage processStorage = ProcessStorage.builder().
                    fileName("fileName"+i).path("//filename"+i).build();
            processStorageList.add(processStorage);

        }
        Map<String, ProcessStorage> processStorageMap = processStorageList.
                stream().
                collect(Collectors.toMap(ProcessStorage::getPath, Function.identity()));
        System.out.println(processStorageMap);
    }
}
