package net.chow.pulsar;

import org.apache.pulsar.client.api.PulsarClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class MyPulsarSourceTest {
    private int i = 1;

    @Mock
    PulsarClient pulsarClient;

    @Before
    public void setPulsarEnvironment() {

    }
    @Test
    public void testOpen() {
        MyPulsarSource source = new MyPulsarSource();
        source.open();
        source.run();
        Assert.assertTrue(source != null);
    }
}