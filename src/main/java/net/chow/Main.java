package net.chow;

import net.chow.pulsar.MyPulsarSource;
import net.chow.task.CTe1;
import net.chow.task.TFactory;

import java.util.concurrent.*;

public class Main {
    public static void main(String []args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(
                1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1), new TFactory());
        // 如果supplyAsync在调用thenAccept前就complete，则thenAccept会阻塞
        // 反正不会
        // 即使用thenAccept时，前面步骤过快则会阻塞在thenAccept处
        CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println(1);
            return 1;
        }, exec).thenAcceptAsync(e -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e + 1);
        }, exec);

        System.out.println("out");
        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        exec.shutdown();
        System.out.println("exit");
    }
}
