package Model;

public abstract class Vehicle {
    int position, speed;
    int carBreadth = 1;
    private int vehicleNum;
    String vehicleType = "";

    public Vehicle(int position, int vehicleNum, int speed) {
        this.position = position;
        this.speed = speed;
        this.vehicleNum = vehicleNum;
    }

    //accelerates or drives at full speed
    public abstract void drive();

    //decelerates or doesnt move if stopped
    public abstract void stop();

    public abstract int getMAXSPEED();

    /*    abstract int getLength();*/

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
