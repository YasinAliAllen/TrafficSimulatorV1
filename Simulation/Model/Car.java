package Model;

public class Car extends Vehicle {
    private final int MAXSPEED = 6, ACCELERATION = 2, DECELERATION = 4;
    private int length = 2 * carBreadth;

    public Car(int position, int vehicleNum, int speed) {
        super(position, vehicleNum, speed);
        vehicleType = "Car";
    }

    @Override
    public void drive() {
        if (speed + ACCELERATION < MAXSPEED)
            speed += ACCELERATION;
        else
            speed = MAXSPEED;
        position += speed;
    }

    @Override
    public void stop() {
        if (speed - DECELERATION > 0)
            speed -= DECELERATION;
        else
            speed = 0;
        position += speed;
    }

    @Override
    public int getMAXSPEED() {
        return MAXSPEED;
    }

    @Override
    int getDeceleration() {
        return DECELERATION;
    }

    @Override
    public int getAcceleration() {
        return ACCELERATION;
    }
}