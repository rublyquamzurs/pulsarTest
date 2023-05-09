package net.chow;

import net.chow.task.CTe1;
import net.chow.task.TFactory;

import java.util.concurrent.*;

public class Main {
    public static void main(String []args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(
                10, 10, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1), new ThreadPoolExecutor.AbortPolicy());
        long start = System.currentTimeMillis();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        CTe1 t = new CTe1(exec, start);
        executor.scheduleWithFixedDelay(t, 0, 1, TimeUnit.SECONDS);
    }
}
