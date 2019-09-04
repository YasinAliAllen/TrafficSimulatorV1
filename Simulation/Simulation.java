import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private final int MAXSPEED = 3, STOPTIME = 100, NUMROADS = 10, NUMCARS = 2, NUMLIGHTS = 3;
    int time, lightsRoad, lightsPosition, carsRoad, carPosition;
    private ArrayList<RoadStraight> roads = new ArrayList<>();//array storing roads
    private ArrayList<Integer> lightsRoads = new ArrayList<>();//array with random values to assign roads for lights to
    // be on
    private ArrayList<Integer> carsRoads = new ArrayList<>();//array with random values to assign roads for cars to
    // be on
    private Random random = new Random();

    void createSimulation() {
        for (int i = 0; i < NUMLIGHTS; i++) {
            lightsRoad = random.nextInt(NUMROADS);
            lightsRoads.add(lightsRoad);
        }
        for (int c = 0; c < NUMCARS; c++) {
            carsRoad = random.nextInt(NUMROADS / 2);
            carsRoads.add(carsRoad);
        }

        for (int i = 0; i < NUMROADS; i++) {
            RoadStraight roadStraight = new RoadStraight();
            for (int c = 0; c < NUMCARS; c++) { // creates cars
                if (roads.size() == carsRoads.get(c)) {
                    carPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createVehicle("Car", carPosition, c);
                    System.out.println("Car " + c + " created at position " + carPosition + " on road " + i);
                }
            }

            for (int j = 0; j < NUMLIGHTS; j++) { // creates a traffic light on a road
                if (roads.size() == lightsRoads.get(j)) { //checks which road to create a light on
                    lightsPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createTrafficLight(lightsPosition);
                    System.out.println("Light " + j + " created on road " + i + " at position " + lightsPosition);
                }
            }
            roads.add(roadStraight);
        }
    }

    void startSimulation() {
        while (time < STOPTIME) {
            for (int i = 0; i < roads.size(); i++) { //moves any vehicle on a road
                for (int c = 0; c < roads.get(i).countVehicles(); c++) {
                    if (roads.get(i).getVehicle(c) != null) { //checks for vehicles
                        System.out.println("Car: " + roads.get(i).getVehicle(c).getVehicleNum() + " Road: " + i +
                                " Position: " + roads.get(i).getVehicle(c).getPosition());
                        if (roads.get(i).getVehicle(c).getPosition() + roads.get(i).getVehicle(c).getSpeed()
                                > roads.get(i).getLength()) {
                            if (roads.get(i).getVehicle(c).getSpeed() == MAXSPEED) { //checks if car needs to accelerate
                                roads.get(i + 1).createVehicle("Car",
                                        (roads.get(i).getVehicle(c).getPosition() +
                                                roads.get(i).getVehicle(c).getSpeed()) % roads.get(i).getLength(),
                                        roads.get(i).getVehicle(c).getVehicleNum()); //Creates car on next road
                                roads.get(i + 1).getVehicle(c).setSpeed(roads.get(i).getVehicle(c).getSpeed()); //Sets new cars speed
                                roads.get(i).destroyVehicle(c); //Removes old car from road
                            } else {
                                roads.get(i + 1).createVehicle("Car",
                                        (roads.get(i).getVehicle(c).getPosition() +
                                                roads.get(i).getVehicle(c).getSpeed() +
                                                roads.get(i).getVehicle(c).getAcceleration()) % roads.get(i).getLength(),
                                        roads.get(i).getVehicle(c).getVehicleNum()); //Creates car on next road
                                roads.get(i + 1).getVehicle(c).setSpeed(roads.get(i).getVehicle(c).getSpeed() +
                                        roads.get(i).getVehicle(c).getAcceleration()); //Sets new cars speed
                                roads.get(i).destroyVehicle(c); //Removes old car from road
                            }
                        }
                        if (roads.get(i).getVehicle(c) != null) { //checks for vehicle
                            if (roads.get(i).getTrafficLight() == null || roads.get(i).getTrafficLight().isStatus()) // checks for green light or no light
                                roads.get(i).getVehicle(c).drive(MAXSPEED);
                            else {
                                if ((roads.get(i).getVehicle(c).getPosition() <= //checks if car needs to stop before light
                                        roads.get(i).getTrafficLight().getPosition()) &&
                                        (roads.get(i).getVehicle(c).getPosition() +
                                                roads.get(i).getVehicle(0).getSpeed() >=
                                                roads.get(i).getTrafficLight().getPosition())) {
                                    roads.get(i).getVehicle(c).stop();
                                } else
                                    roads.get(i).getVehicle(c).drive(MAXSPEED);
                            }
                        }
                        time++; //updates timer after every car is moved
                        if (time % 25 == 0) {
                            for (int j = 0; j < NUMLIGHTS; j++) {
                                roads.get(lightsRoads.get(j)).getTrafficLight().toggleColour();
                                System.out.println("Light " + j + " on road " + lightsRoads.get(j) + " At position " +
                                        roads.get(lightsRoads.get(j)).getTrafficLight().getPosition() + " toggled");
                            }
                        }
                    }
                }
            }
        }
    }
}



