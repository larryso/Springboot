package com.larry.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class GuavaCacheStore<T> {
    private Cache<String, T> cache;

    public GuavaCacheStore(int expirationDuration, TimeUnit timeUnit){
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expirationDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public T get(String key){
        return cache.getIfPresent(key);
    }

    public void add(String key, T value){
        if(key != null & value != null){
            cache.put(key, value);
        }
    }
}
