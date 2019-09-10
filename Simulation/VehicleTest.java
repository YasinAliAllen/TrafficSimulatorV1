import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    private Vehicle vehicle = new Vehicle(0, 0, 1, 0, 1, 2);
    private int position = 0;

    @org.junit.jupiter.api.Test
    void drive() {
        for (int i = 0; i < 3; i++) {
            assertEquals(i, vehicle.getSpeed());
            assertEquals(position, vehicle.getPosition());
            vehicle.drive(3);
            position += vehicle.getSpeed();
        }
    }

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
    void getPosition() {
        assertEquals(0, vehicle.getPosition());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDeceleration() {
        assertEquals(2, vehicle.getDeceleration());
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        vehicle.setSpeed(100);
        assertEquals(100, vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getVehicleNum() {
        assertEquals(0, vehicle.getVehicleNum());
    }

    @org.junit.jupiter.api.Test
    void getAcceleration() {
        assertEquals(1, vehicle.getAcceleration());
    }
}