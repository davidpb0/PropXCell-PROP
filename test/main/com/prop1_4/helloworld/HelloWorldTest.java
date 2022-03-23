package main.com.prop1_4.helloworld;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {
    @Test
    void testSumaBuena() {
        int sum = HelloWorld.suma(1, 1);
        assertEquals(2, sum);
    }

    @Test
    void testSumaMala() {
        int sum = HelloWorld.suma(1, 1);
        assertNotEquals(3, sum);
    }
}