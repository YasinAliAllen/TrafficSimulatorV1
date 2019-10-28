import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    private Vehicle vehicle = new Car(0, 0, 1);
    private int position = 0;

    @Test
    void drive() {
        for (int i = 0; i < 3; i++) {
            assertEquals(i, vehicle.getSpeed());
            assertEquals(position, vehicle.getPosition());
            vehicle.drive();
            position += vehicle.getSpeed();
        }
    }

    @Test
    void stop() {
        int speed = 5;
        vehicle.setSpeed(speed);
        while (vehicle.getSpeed() > 0) {
            assertEquals(position, vehicle.getPosition());
            assertEquals(speed, vehicle.getSpeed());
            vehicle.stop();
            speed -= vehicle.getDeceleration();
            position += vehicle.getSpeed();
        }

    }

    @Test
    void getPosition() {
        assertEquals(0, vehicle.getPosition());
    }

    @Test
    void getSpeed() {
        assertEquals(0, vehicle.getSpeed());
    }

    @Test
    void getDeceleration() {
        assertEquals(2, vehicle.getDeceleration());
    }

    @Test
    void setSpeed() {
        vehicle.setSpeed(100);
        assertEquals(100, vehicle.getSpeed());
    }

    @Test
    void getVehicleNum() {
        assertEquals(0, vehicle.getVehicleNum());
    }

    @Test
    void getAcceleration() {
        assertEquals(1, vehicle.getAcceleration());
    }
}