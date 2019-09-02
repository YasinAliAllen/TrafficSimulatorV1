public class Vehicle {
    private int position, length, breadth, speed, acceleration, deceleration;

    public Vehicle() {
        position = 0;
        breadth = 1;
        length = 2 * breadth;
        speed = 0;
        acceleration = 1;
        deceleration = 2;
    }

    public Vehicle(int position, int length, int breadth, int speed, int acceleration, int deceleration) {
        this.position = position;
        this.length = length;
        this.breadth = breadth;
        this.speed = speed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
    }

    public Vehicle(int position) {
        this.position = position;
        breadth = 1;
        length = 2 * breadth;
        speed = 0;
        acceleration = 1;
        deceleration = 2;
    }

    public void drive(int maxSpeed) {
        position += speed;
        if (speed + acceleration < maxSpeed)
            speed += acceleration;
        else
            speed = maxSpeed;
    }

    public void stop() {
        if (speed - deceleration > 0)
            speed -= deceleration;
        else
            speed = 0;
    }

    public int getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDeceleration() {
        return deceleration;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}