public class Vehicle {
    private int position, length, breadth, speed, acceleration, deceleration;

    public Vehicle() {
        position = 0;
        breadth = 1;
        length = 2 * breadth;
        speed = 0;
        acceleration = 5;
        deceleration = 10;
    }

    public Vehicle(int position, int length, int breadth, int speed, int acceleration, int deceleration) {
        this.position = position;
        this.length = length;
        this.breadth = breadth;
        this.speed = speed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
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
}
