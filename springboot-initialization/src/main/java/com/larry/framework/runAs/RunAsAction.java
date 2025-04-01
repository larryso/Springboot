package com.larry.framework.runAs;
@FunctionalInterface
public interface RunAsAction<T>{
     T execute();
}
