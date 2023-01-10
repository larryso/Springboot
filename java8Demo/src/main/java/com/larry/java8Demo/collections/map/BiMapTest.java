package com.larry.java8Demo.collections.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

public class BiMapTest{
    public static void main(String[] args) {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(new Integer(1), "One");
        //Throws IllegalArgumentException if the given value is already bound to a different key in this bimap
        //biMap.put(new Integer(2), "One");
        biMap.put(new Integer(2), "Two");
        biMap.put(new Integer(3), "Three");
        biMap.put(new Integer(4), "Four");
        System.out.println(biMap);
        biMap.forcePut(new Integer(4), "Four");//
        System.out.println(biMap);
        Map<Integer, String> old = new HashMap();
        old.put(5, "Five");
        old.put(6, "Six");
        biMap.putAll(old);
        System.out.println(biMap);
        

    }
}
