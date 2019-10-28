public class Motorbike extends Vehicle {
    private final int MAXSPEED = 8, ACCELERATION = 4, DECELERATION = 4;
    private int length = carBreadth;

    public Motorbike(int position, int vehicleNum, int speed) {
        super(position, vehicleNum, speed);
    }

    @Override
    void drive() {
        if (speed + ACCELERATION < MAXSPEED)
            speed += ACCELERATION;
        else
            speed = MAXSPEED;
        position += speed;
    }

    @Override
    void stop() {
        if (speed - DECELERATION > 0)
            speed -= DECELERATION;
        else
            speed = 0;
        position += speed;
    }

    @Override
    int getMAXSPEED() {
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
    int getAcceleration() {
        return ACCELERATION;
    }
}