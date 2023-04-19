package net.chow;

import net.chow.pulsar.MyPulsarSource;
import net.chow.task.CTe1;
import net.chow.task.TFactory;

import java.util.concurrent.*;

public class Main {
    public static void main(String []args) {
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        CTe1 t = new CTe1();
        exec.scheduleWithFixedDelay(t, 1, 1, TimeUnit.SECONDS);

        ThreadFactory factory = Executors.privilegedThreadFactory();
        ThreadPoolExecutor texec = new ThreadPoolExecutor(
                1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1), new TFactory());
        texec.execute(t);
    }
}
