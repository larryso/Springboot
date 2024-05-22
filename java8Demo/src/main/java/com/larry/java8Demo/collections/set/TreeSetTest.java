package com.larry.java8Demo.collections.set;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("G");
        treeSet.add("E");
        treeSet.add("E");
        treeSet.add("K");
        treeSet.add("S");
        treeSet.add("4");
        System.out.println(treeSet);

        Set<String> treeSet2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeSet2.add("G");
        treeSet2.add("E");
        treeSet2.add("E");
        treeSet2.add("K");
        treeSet2.add("S");
        treeSet2.add("4");
        System.out.println(treeSet2);
    }
}
