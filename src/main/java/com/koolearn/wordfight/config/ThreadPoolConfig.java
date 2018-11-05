package com.koolearn.wordfight.config;

import com.koolearn.wordfight.thread.VisiableThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 20:06
 */
@Configuration
@Slf4j
public class ThreadPoolConfig implements AsyncConfigurer {

    @Bean
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        //使用VisiableThreadPoolTaskExecutor
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(100);
        //配置队列大小
        executor.setQueueCapacity(10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        //等待任务在关机时完成--表明等待所有线程执行完
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止


        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


}
