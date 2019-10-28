public abstract class Vehicle {
    public int position, speed, vehicleNum, carBreadth = 1;

    public Vehicle(int position, int vehicleNum, int speed) {
        this.position = position;
        this.speed = speed;
        this.vehicleNum = vehicleNum;
    }

    abstract void drive();

    abstract void stop();

    abstract int getMAXSPEED();

    abstract int getLength();

    int getPosition() {
        return position;
    }

    int getSpeed() {
        return speed;
    }

    abstract int getDeceleration();

    void setSpeed(int speed) {
        this.speed = speed;
    }

    int getVehicleNum() {
        return vehicleNum;
    }

    abstract int getAcceleration();
}
