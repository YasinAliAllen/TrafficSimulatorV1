package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLightTest {
    private TrafficLight trafficLight = new TrafficLight(0, 0);

    @Test
    void toggleColour() {
        assertFalse(trafficLight.isStatus());
        trafficLight.toggleColour();
        assertTrue(trafficLight.isStatus());
    }

    @Test
    void getPosition() {
        assertEquals(0, trafficLight.getPosition());
    }

    @Test
    void isStatus() {
        assertFalse(trafficLight.isStatus());
    }

    @Test
    void getLightNumber() {
        assertEquals(0, trafficLight.getLightNumber());
    }
}