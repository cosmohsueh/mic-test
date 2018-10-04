package com.cosmo.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.cosmo.init.SysConfig;

@Configuration
@EnableAsync
public class TaskExecutePool {

    @Autowired
    private SysConfig sysConfig;

    @Bean
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(sysConfig.getCorePoolSize());
        executor.setMaxPoolSize(sysConfig.getMaxPoolSize());
        executor.setQueueCapacity(sysConfig.getQueueCapacity());
        executor.setKeepAliveSeconds(sysConfig.getKeepAliveSeconds());
        executor.setThreadNamePrefix("MyExecutor-");

        // rejection-policy：當pool已達到最大容量，該如何處理新任務
        // AbortPolicy：丟出java.util.concurrent.RejectedExecutionException異常
        // CallerRunsPolicy：重試當前任務
        // DiscardOldestPolicy：丟棄舊的任務
        // DiscardPolicy：丟棄當前任務
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
