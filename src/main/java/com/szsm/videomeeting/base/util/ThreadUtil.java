package com.szsm.videomeeting.base.util;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/8/15.
 */
public class ThreadUtil {

    /**
     * 对线程池的最大线程个数做限制
     * @author zhangjinxiang
     * @date 2019-6-24
     */
    private static final ExecutorService executor = new ThreadPoolExecutor(
            10,
            100,
            30L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void execute(Runnable runnable) throws Exception {
        executor.execute(runnable);
    }

    public static <T> Future<T> submit(Callable<T> task) throws Exception {
        return executor.submit(task);
    }
/*
    public static void closed(){
        if (!executor.isShutdown()){
            executor.shutdown();
        }
    }*/

}
