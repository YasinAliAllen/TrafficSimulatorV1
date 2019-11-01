package Model;

public abstract class Vehicle {
    public int position, speed, vehicleNum, carBreadth = 1;
    String vehicleType = "";

    public Vehicle(int position, int vehicleNum, int speed) {
        this.position = position;
        this.speed = speed;
        this.vehicleNum = vehicleNum;
    }

    public abstract void drive();

    public abstract void stop();

    public abstract int getMAXSPEED();

    abstract int getLength();

    public int getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    abstract int getDeceleration();

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVehicleNum() {
        return vehicleNum;
    }

    public abstract int getAcceleration();

    public String getVehicleType() {
        return vehicleType;
    }
}
