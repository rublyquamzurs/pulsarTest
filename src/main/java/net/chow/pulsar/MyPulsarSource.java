package net.chow.pulsar;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPulsarSource {
    Logger logger = LoggerFactory.getLogger(MyPulsarSource.class);
    private int taskId;
    private PulsarAdmin admin;
    private PulsarClient client;
    private Consumer<byte[]> consumer;

    public MyPulsarSource() {
        taskId = 0;
    }

    public void open() {
        try {
            admin = PulsarAdmin.builder().serviceHttpUrl("http://127.0.0.1:8080").build();
            client = PulsarClient.builder().serviceUrl("pulsar://127.0.0.1:6650").build();
            consumer = client.newConsumer()
                .topic("persistent://1/default/2")
                .subscriptionType(SubscriptionType.Shared)
                .subscriptionMode(SubscriptionMode.Durable)
                .subscriptionName("abc")
                .subscribe();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void run() {
        while (true) {
            System.out.println("ok");
            try {
                Message msg = consumer.receive();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
