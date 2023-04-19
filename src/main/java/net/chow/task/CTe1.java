package net.chow.task;

public class CTe1 extends Thread {

    private int count;
    public CTe1() {
        super(CTe1.class.getName());
        count = 0;
    }

    @Override
    public void run() {
        System.out.println(count);
        count++;
        throw new NoClassDefFoundError();
    }
}
