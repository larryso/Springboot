package com.larry.configure;

import com.larry.configure.properties.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration {

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor(AppConfiguration appConfiguration){
        Task task = appConfiguration.getTask();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(task.getPoolCoreSize());
        executor.setMaxPoolSize(task.getPoolMaxSize());
        executor.setQueueCapacity(task.getQueueCapacity());
        executor.setThreadNamePrefix(task.getThreadNamePrefix());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
