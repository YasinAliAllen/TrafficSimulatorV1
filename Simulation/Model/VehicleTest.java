package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {
    //only drive and stop methods are different for different vehicles
    private Vehicle car = new Car(0, 0, 0);
    private Vehicle motorbike = new Motorbike(0, 0, 0);
    private Vehicle bus = new Bus(0, 0, 0);
    private int carPosition = 0, motorbikePosition = 0, busPosition = 0;

    @Test
    void drive() {
        for (int i = 0; i < 3; i++) {
            assertEquals(i * 2, car.getSpeed());
            assertEquals(carPosition, car.getPosition());
            car.drive();
            carPosition += car.getSpeed();

            assertEquals(i * 4, motorbike.getSpeed());
            assertEquals(motorbikePosition, motorbike.getPosition());
            motorbike.drive();
            motorbikePosition += motorbike.getSpeed();

            assertEquals(i, bus.getSpeed());
            assertEquals(busPosition, bus.getPosition());
            bus.drive();
            busPosition += bus.getSpeed();
        }
    }

    @Test
    void stop() {
        int carSpeed = 5;
        car.setSpeed(carSpeed);
        while (car.getSpeed() > 0) {
            assertEquals(carPosition, car.getPosition());
            assertEquals(carSpeed, car.getSpeed());
            car.stop();
            carSpeed -= car.getDeceleration();
            carPosition += car.getSpeed();
        }

        int motorbikeSpeed = 10;
        motorbike.setSpeed(motorbikeSpeed);
        while (motorbike.getSpeed() > 0) {
            assertEquals(motorbikePosition, motorbike.getPosition());
            assertEquals(motorbikeSpeed, motorbike.getSpeed());
            motorbike.stop();
            motorbikeSpeed -= motorbike.getDeceleration();
            motorbikePosition += motorbike.getSpeed();
        }

        int busSpeed = 4;
        bus.setSpeed(busSpeed);
        while (bus.getSpeed() > 0) {
            assertEquals(busPosition, bus.getPosition());
            assertEquals(busSpeed, bus.getSpeed());
            bus.stop();
            busSpeed -= bus.getDeceleration();
            busPosition += bus.getSpeed();
        }


    }

    @Test
    void getPosition() {
        assertEquals(0, car.getPosition());
    }

    @Test
    void getSpeed() {
        assertEquals(0, car.getSpeed());
    }

    @Test
    void getDeceleration() {
        assertEquals(4, car.getDeceleration());
    }

    @Test
    void setSpeed() {
        car.setSpeed(100);
        assertEquals(100, car.getSpeed());
    }

    @Test
    void getVehicleNum() {
        assertEquals(0, car.getVehicleNum());
    }

    @Test
    void getAcceleration() {
        assertEquals(2, car.getAcceleration());
    }
}