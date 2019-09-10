import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private final int MAXSPEED = 3, STOPTIME = 1000, NUMROADS = 10, NUMCARS = 2, NUMLIGHTS = 3;
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
            carsRoad = 0;//random.nextInt(NUMROADS / 2);
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
                    roadStraight.createTrafficLight(j, lightsPosition);
                    System.out.println("Light " + j + " created on road " + i + " at position " + lightsPosition);
                }
            }
            roads.add(roadStraight);
        }
    }

    private void changeRoads(int road, int vehicle) {
        roads.get(road + 1).createVehicle("Car",
                (roads.get(road).getVehicle(vehicle).getPosition() +
                        roads.get(road).getVehicle(vehicle).getSpeed()) % roads.get(road).getLength(),
                roads.get(road).getVehicle(vehicle).getVehicleNum()); //Creates car on next road
        roads.get(road + 1).getVehicle(vehicle).setSpeed(roads.get(road).getVehicle(vehicle).getSpeed());
        //Sets new cars speed
        roads.get(road).destroyVehicle(vehicle); //Removes old car from old road
    }

    private void changeRoadsAccelerating(int road, int vehicle) {
        roads.get(road + 1).createVehicle("Car",
                (roads.get(road).getVehicle(vehicle).getPosition() +
                        roads.get(road).getVehicle(vehicle).getSpeed() +
                        roads.get(road).getVehicle(vehicle).getAcceleration()) %
                        roads.get(road).getLength(),
                roads.get(road).getVehicle(vehicle).getVehicleNum()); //Creates car on next road
        roads.get(road + 1).getVehicle(vehicle).setSpeed(roads.get(road).getVehicle(vehicle).getSpeed() +
                roads.get(road).getVehicle(vehicle).getAcceleration()); //Sets new cars speed
        roads.get(road).destroyVehicle(vehicle); //Removes old car from road
    }

    void startSimulation() {
        while (time < STOPTIME) {
            for (int i = 0; i < roads.size(); i++) { //moves any vehicle on a road
                for (int c = 0; c < NUMCARS; c++) {
                    if (roads.get(i).getVehicle(c) != null) { //checks for vehicles
                        System.out.println("Car: " + roads.get(i).getVehicle(c).getVehicleNum() + " Road: " + i +
                                " Position: " + roads.get(i).getVehicle(c).getPosition());

                        if (roads.get(i).getVehicle(c).getSpeed() == MAXSPEED) //checks if car needs to accelerate
                        {
                            if (roads.get(i).getVehicle(c).getPosition() + roads.get(i).getVehicle(c).getSpeed()
                                    > roads.get(i).getLength()) //checks if car needs to swap roads
                                changeRoads(i, c);
                        } else {
                            if (roads.get(i).getVehicle(c).getPosition() + roads.get(i).getVehicle(c).getSpeed() + roads.get(i).getVehicle(c).getAcceleration()
                                    > roads.get(i).getLength()) {
                                changeRoadsAccelerating(i, c);
                            }
                        }
                    }
                    if (roads.get(i).getVehicle(c) != null) { //checks for vehicle
                        if (roads.get(i).countLights() != 0) {
                            for (int j = 0; j < roads.get(i).countLights(); j++) {
                                for (int t = 0; t < NUMLIGHTS; t++) {
                                    if (roads.get(i).getTrafficLight(t) != null)
                                        if (roads.get(i).getTrafficLight(t).isStatus()) // checks for green light or no light
                                            roads.get(i).getVehicle(c).drive(MAXSPEED);
                                        else {
                                            if ((roads.get(i).getVehicle(c).getPosition() <= //checks if car needs to stop before light
                                                    roads.get(i).getTrafficLight(t).getPosition()) &&
                                                    (roads.get(i).getVehicle(c).getPosition() +
                                                            roads.get(i).getVehicle(c).getSpeed() >=
                                                            roads.get(i).getTrafficLight(t).getPosition())) {
                                                roads.get(i).getVehicle(c).stop();
                                            } else
                                                roads.get(i).getVehicle(c).drive(MAXSPEED);
                                        }
                                }
                            }
                        } else {
                            roads.get(i).getVehicle(c).drive(MAXSPEED);
                        }
                    }
                }
                if (time % 25 == 0) {
                    if (roads.get(i).countLights() != 0)
                        for (int t = 0; t < NUMLIGHTS; t++) {
                            if (roads.get(i).getTrafficLight(t) != null) {
                                roads.get(i).getTrafficLight(t).toggleColour();
                                System.out.println("Light " + t + " on road " + i + " At position " +
                                        roads.get(i).getTrafficLight(t).getPosition() + " toggled");

                            }
                        }
                }
            }
            time++; //updates timer after every car is moved

        }
    }
}




