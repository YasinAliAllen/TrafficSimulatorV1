package Model;

public class Bus extends Vehicle {
    private final int MAXSPEED = 4, ACCELERATION = 1, DECELERATION = 2;
    private int length = 6 * carBreadth;

    public Bus(int position, int vehicleNum, int speed) {
        super(position, vehicleNum, speed);
        vehicleType = "Bus";
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