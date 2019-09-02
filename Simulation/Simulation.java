import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    int speedMax, time;
    private final int MAXSPEED = 3, STOPTIME = 50, NUMROADS = 5, NUMCARS = 1;
    private ArrayList<RoadStraight> roads = new ArrayList<>();
    private Random random = new Random();

    void createSimulation() {
        for (int i = 0; i < NUMROADS; i++) {
            RoadStraight roadStraight = new RoadStraight();
            if (i < NUMCARS)
                roadStraight.createVehicle("Car", random.nextInt(roadStraight.getLength()));
            roadStraight.createTrafficLight();
            roads.add(roadStraight);
        }
    }

    void startSimulation() {
        while (time < STOPTIME) {
            for (int i = 0; i < roads.size(); i++) { //moves any vehicle on a road
                if (roads.get(i).getVehicle() != null) { //checks for vehicle
                    System.out.println(i + " " + roads.get(i).getVehicle().getPosition());
                    if (roads.get(i).getVehicle().getPosition() + roads.get(i).getVehicle().getSpeed()
                            > roads.get(i).getLength()) {
                        roads.get(i + 1).createVehicle("Car", (roads.get(i).getVehicle().getPosition() +
                                roads.get(i).getVehicle().getSpeed()) % roads.get(i).getLength()); //Creates car on next road
                        roads.get(i + 1).getVehicle().setSpeed(roads.get(i).getVehicle().getSpeed()); //Sets new cars speed
                        roads.get(i).destroyVehicle(roads.get(i).getVehicle().getPosition()); //Removes old car from road
                    }
                    if (roads.get(i).getVehicle() != null) { //checks for vehicle

                        if (roads.get(i).getVehicle().getPosition() + roads.get(i).getVehicle().getSpeed() > (
                                roads.get(i).getTrafficLight().getPosition() -
                                        roads.get(i).getVehicle().getSpeed() /
                                                roads.get(i).getVehicle().getDeceleration()) &&
                                roads.get(i).getTrafficLight().status)
                            roads.get(i).getVehicle().stop();
                        roads.get(i).getVehicle().drive(MAXSPEED);
                    }
                    time++;
                }
            }
        }
    }
}
