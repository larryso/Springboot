package com.larry.test;

import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class JunitTestDemo {
    @Tag("Slow")
    @Test
    public void testAddMaxInteger(){
        System.out.println("Tag Slow Test");
        Assert.assertEquals(43, Integer.sum(19, 23));
    }

    @Tag("Fast")
    @Test
    public void testDivide(){
        System.out.println("Tag Fast Test");
        Assert.assertEquals(4, Integer.divideUnsigned(16, 4));
    }
}
