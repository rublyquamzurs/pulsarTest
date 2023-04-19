package net.chow.task;

import java.util.concurrent.ThreadFactory;

public class TFactory implements ThreadFactory {
    private int count = 0;
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "t" + count++);
    }
}
