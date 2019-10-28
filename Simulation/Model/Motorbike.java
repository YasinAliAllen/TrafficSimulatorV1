package Model;

public class Motorbike extends Vehicle {
    private final int MAXSPEED = 8, ACCELERATION = 4, DECELERATION = 4;
    private int length = carBreadth;

    public Motorbike(int position, int vehicleNum, int speed) {
        super(position, vehicleNum, speed);
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
    int getLength() {
        return length;
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