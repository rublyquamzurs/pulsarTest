package net.chow.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class CTe1 extends Thread {

    private final ThreadPoolExecutor exec;
    private final long start;
    public CTe1(ThreadPoolExecutor exec, long start) {
        super(CTe1.class.getName());
        this.exec = exec;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            long cur = System.currentTimeMillis();
            System.out.printf("Margin: %d%n", cur - start);

            CompletableFuture<Integer> ret = CompletableFuture.supplyAsync(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
                System.out.println(1);
                return 1;
            }, exec);
            Thread.sleep(10);

            // 如果supplyAsync在调用thenAccept前就complete，则thenAccept会阻塞
            // 反正不会
            // 即使用thenAccept时，前面步骤过快则会阻塞在thenAccept处
            // 但只要不阻塞，都不会影响外层scheduleWithFixedDelay的调用频率
            ret.thenAcceptAsync(e -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(e + 1);
            }, exec);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
