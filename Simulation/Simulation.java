import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private final int MAXSPEED = 3, STOPTIME = 100, NUMROADS = 10, NUMCARS = 1, NUMLIGHTS = 3;
    int time, lightsRoad, lightsPosition, carPosition;
    private ArrayList<RoadStraight> roads = new ArrayList<>();
    private ArrayList<Integer> trafficLights = new ArrayList<>();
    private Random random = new Random();

    void createSimulation() {
        for (int i = 0; i < NUMLIGHTS; i++) {
            lightsRoad = random.nextInt(NUMROADS);
            trafficLights.add(lightsRoad);
        }

        for (int i = 0; i < NUMROADS; i++) {
            RoadStraight roadStraight = new RoadStraight();
            if (i < NUMCARS) { // creates cars
                carPosition = random.nextInt(roadStraight.getLength());
                roadStraight.createVehicle("Car", carPosition);
                System.out.println("Car " + i + " created at position " + carPosition + " on road " + i);
            }

            for (int j = 0; j < NUMLIGHTS; j++) { // creates a traffic light on a road
                if (roads.size() == trafficLights.get(j)) {
                    lightsPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createTrafficLight(lightsPosition);
                    System.out.println("Light " + j + " created on road " + trafficLights.get(j) + " at position " + lightsPosition);
                }
            }
            roads.add(roadStraight);
        }
    }

    void startSimulation() {
        while (time < STOPTIME) {
            for (int i = 0; i < roads.size(); i++) { //moves any vehicle on a road
                if (roads.get(i).getVehicle() != null) { //checks for vehicle
                    System.out.println("Road: " + i + " Position: " + roads.get(i).getVehicle().getPosition());
                    if (roads.get(i).getVehicle().getPosition() + roads.get(i).getVehicle().getSpeed()
                            > roads.get(i).getLength()) {
                        roads.get(i + 1).createVehicle("Car", (roads.get(i).getVehicle().getPosition() +
                                roads.get(i).getVehicle().getSpeed()) % roads.get(i).getLength()); //Creates car on next road
                        roads.get(i + 1).getVehicle().setSpeed(roads.get(i).getVehicle().getSpeed()); //Sets new cars speed
                        roads.get(i).destroyVehicle(roads.get(i).getVehicle().getPosition()); //Removes old car from road
                    }
                    if (roads.get(i).getVehicle() != null) { //checks for vehicle
                        if (roads.get(i).getTrafficLight() == null || roads.get(i).getTrafficLight().isStatus()) // checks for green light or no light
                            roads.get(i).getVehicle().drive(MAXSPEED);
                        else {
                            if ((roads.get(i).getVehicle().getPosition() <= //checks if car needs to stop before light
                                    roads.get(i).getTrafficLight().getPosition()) &&
                                    (roads.get(i).getVehicle().getPosition() + roads.get(i).getVehicle().getSpeed() >=
                                            roads.get(i).getTrafficLight().getPosition())) {
                                roads.get(i).getVehicle().stop();
                            }
                            else
                                roads.get(i).getVehicle().drive(MAXSPEED);
                        }
                        time++;
                        if (time % 25 == 0) {
                            for (int j = 0; j < NUMLIGHTS; j++) {
                                roads.get(trafficLights.get(j)).getTrafficLight().toggleColour();
                                System.out.println("Light " + j + " on road " + trafficLights.get(j) + " toggled");
                            }
                        }
                    }
                }
            }
        }
    }
}



