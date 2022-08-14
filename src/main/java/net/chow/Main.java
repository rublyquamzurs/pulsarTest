package net.chow;

import net.chow.pulsar.MyPulsarSource;

public class Main {
    public static void main(String []args) {
        MyPulsarSource source = new MyPulsarSource();
        source.open();
        System.out.println("open ok");
    }
}
