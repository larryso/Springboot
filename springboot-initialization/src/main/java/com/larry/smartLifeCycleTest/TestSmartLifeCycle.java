package com.larry.smartLifeCycleTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;

@Slf4j
public class TestSmartLifeCycle implements SmartLifecycle {
    private boolean isRunning = false;
    /**
     * 1. 咱們主要在該方法中啓動任務或者其餘異步服務，好比開啓MQ接收消息<br/>
     * 2. 當上下文被刷新（全部對象已被實例化和初始化以後）時，將調用該方法，默認生命週期處理器將檢查每一個SmartLifecycle對象的isAutoStartup()方法返回的布爾值。
     * 若是爲「true」，則該方法會被調用，而不是等待顯式調用本身的start()方法。
     */
    @Override
    public void start() {
        log.info("Starting running TestSmartLifeCycle Bean......");
        isRunning = true;
    }

    /**
     * 接口Lifecycle的子類的方法，只有非SmartLifecycle的子類纔會執行該方法。<br/>
     * 1. 該方法只對直接實現接口Lifecycle的類才起做用，對實現SmartLifecycle接口的類無效。<br/>
     * 2. 方法stop()和方法stop(Runnable callback)的區別只在於，後者是SmartLifecycle子類的專屬。
     */
    @Override
    public void stop() {

    }

    /**
     * 1. 只有該方法返回false時，start方法纔會被執行。<br/>
     * 2. 只有該方法返回true時，stop(Runnable callback)或stop()方法纔會被執行。
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 根據該方法的返回值決定是否執行start方法。<br/>
     * 返回true時start方法會被自動執行，返回false則不會。
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * SmartLifecycle子類的纔有的方法，當isRunning方法返回true時，該方法纔會被調用。
     */
    @Override
    public void stop(Runnable callback) {
        log.info("Stop Runable...");
        callback.run();
        isRunning = false;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
