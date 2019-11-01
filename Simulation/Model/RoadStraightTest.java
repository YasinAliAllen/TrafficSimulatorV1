package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {
    private Road road = new RoadStraight(100, 0, true);

    @Test
    void createVehicle() {
        road.createVehicle("Car", 0, 0, 1, 0);
        assertNotNull(road.getVehicle(0, 0));
    }

    @Test
    void destroyVehicle() {
        road.createVehicle("Car", 0, 0, 1, 0);
        assertNotNull(road.getVehicle(0, 0));
        road.destroyVehicle(0, 0);
        assertNull(road.getVehicle(0, 0));
    }

    @Test
    void createTrafficLight() {
        road.createTrafficLight(0, 0);
        assertNotNull(road.getTrafficLight(0));
    }

    @Test
    void getLength() {
        assertEquals(100, road.getLength());
    }

    @Test
    void getVehicle() {
        assertNull(road.getVehicle(0, 0));
        road.createVehicle("Car", 0, 0, 1, 0);
        assertNotNull(road.getVehicle(0, 0));
    }

    @Test
    void countVehicles() {
        for (int i = 0; i < 3; i++) {
            road.createVehicle("Car", 0, i, 1, 0);
            assertEquals(i + 1, road.countVehicles(0));
        }
    }

    @Test
    void countLights() {
        for (int i = 0; i < 3; i++) {
            road.createTrafficLight(i, 0);
            assertEquals(i + 1, road.countLights());
        }
    }

    @Test
    void getTrafficLight() {
        assertNull(road.getTrafficLight(0));
        road.createTrafficLight(0, 0);
        assertNotNull(road.getTrafficLight(0));
    }


}