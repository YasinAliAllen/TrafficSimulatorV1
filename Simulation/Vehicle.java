public class Vehicle {
    public int length, breadth; //these will be used later
    private int position, speed, acceleration, deceleration, vehicleNum;

    public Vehicle(int position, int vehicleNum, int breadth, int speed, int acceleration, int deceleration) {
        this.position = position;
        this.breadth = breadth;
        this.length = 2 * breadth;
        this.speed = speed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.vehicleNum = vehicleNum;
    }

    void drive(int maxSpeed) {
        if (speed + acceleration < maxSpeed)
            speed += acceleration;
        else
            speed = maxSpeed;
        position += speed;
    }

    void stop() {
        if (speed - deceleration > 0)
            speed -= deceleration;
        else
            speed = 0;
        position += speed;
    }

    int getPosition() {
        return position;
    }

    int getSpeed() {
        return speed;
    }

    public int getDeceleration() {
        return deceleration;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    int getVehicleNum() {
        return vehicleNum;
    }

    int getAcceleration() {
        return acceleration;
    }
}
