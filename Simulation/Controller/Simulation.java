package Controller;

import Model.Road;
import Model.RoadFourWay;
import Model.RoadStraight;
import Model.RoadThreeWay;

import java.util.ArrayList;

public class Simulation {
    ArrayList<ArrayList<Road>> roads = new ArrayList<>();//array storing roads
    final int LENGTH = 20;
    private int currentCars = 10;
    private int time;


    private Road roadBuilder(String roadType, int rotations, boolean isSpawner) {
        switch (roadType) {
            case "Road Straight":
                return new RoadStraight(LENGTH, rotations, isSpawner);
            case "Road Three Way":
                return new RoadThreeWay(LENGTH, rotations, isSpawner);
            case "Road Four Way":
                return new RoadFourWay(LENGTH, isSpawner);
            default:
                return null;
        }
    }

    void createSimulation(ArrayList<ArrayList<String>> data, int cars, int motorbikes, int busses) {
        ArrayList<ArrayList<Road>> roads = new ArrayList<>();//array storing roads
        for (int i = 0; i < data.size(); i++) {
            roads.add(new ArrayList<Road>());
            for (int j = 0; j < data.get(i).size(); j++) {
                String roadType = data.get(i).get(j).split(",")[0];
                int rotation = Integer.parseInt(data.get(i).get(j).split(",")[1]);
                boolean isSpawner = Boolean.parseBoolean(data.get(i).get(j).split(",")[2]);
                roads.get(i).add(roadBuilder(roadType, rotation, isSpawner));
            }
        }
    }


        /*


        for (int c = 0; c < NUMCARS; c++) { //randomly selects cars road
            int carsRoad = random.nextInt(NUMROADS / 2);
            carsRoads.add(carsRoad);
        }

        for (int i = 0; i < NUMROADS; i++) {
            final int ROADLENGTH = 100;
            RoadStraight roadStraight = new RoadStraight(ROADLENGTH);
            for (int c = 0; c < NUMCARS; c++) { // creates cars
                if (roads.size() == carsRoads.get(c)) {
                    int carPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createVehicle("Model.Motorbike", carPosition, c, 1, 0);
                    System.out.printf("Model.Car %d created on road %d at position %d.\n", c, i, carPosition);
                }
            }
            for (int j = 0; j < NUMLIGHTS; j++) { // creates a traffic light on a road
                if (roads.size() == lightsRoads.get(j)) { //checks which road to create a light on
                    int lightsPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createTrafficLight(j, lightsPosition);
                    System.out.printf("Light %d created on road %d at position %d.\n", j, i, lightsPosition);
                }
            }
            roads.add(roadStraight);
        }
    }

    private void changeRoads(int roadNum, int vehicleNum) {
        if (roadNum + 1 == NUMROADS) {
            System.out.println("We have run out of road for vehicle " + vehicleNum);
            roads.get(roadNum).destroyVehicle(vehicleNum, LANE);
            currentCars--;
        } else {
            roads.get(roadNum + 1).createVehicle("Model.Car",
                    (roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() +
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed()) % roads.get(roadNum).getLength(),
                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getVehicleNum(),
                    1, LANE); //Creates car on next road
            roads.get(roadNum + 1)
                    .getVehicle(vehicleNum, LANE).setSpeed(roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed());
            //Sets new cars speed
            roads.get(roadNum).destroyVehicle(vehicleNum, LANE); //Removes old car from old road
        }
    }

    private void changeRoadsAccelerating(int roadNum, int vehicleNum) {
        if (roadNum + 1 == NUMROADS) {
            System.out.println("We have run out of road for vehicle " + vehicleNum);
            roads.get(roadNum).destroyVehicle(vehicleNum, LANE);
            currentCars--;

        } else {
            roads.get(roadNum + 1).createVehicle("Model.Car",
                    (roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() +
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed() +
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).getAcceleration()) %
                            roads.get(roadNum).getLength(),
                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getVehicleNum(), 1,
                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed() +
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).getAcceleration()); //Creates car on next road
            roads.get(roadNum).destroyVehicle(vehicleNum, LANE); //Removes old car from road
        }
    }

    private void toggleLights(int roadNum) {
        if (roads.get(roadNum).countLights() != 0)
            for (int t = 0; t < NUMLIGHTS; t++) {
                if (roads.get(roadNum).getTrafficLight(t) != null) {
                    roads.get(roadNum).getTrafficLight(t).toggleColour();
                    System.out.printf("Light %d on road %d at position %d toggled.\n", t, roadNum,
                            roads.get(roadNum).getTrafficLight(t).getPosition());
                }
            }
    }

    private void checkForLight(int roadNum, int vehicleNum) {
        for (int j = 0; j < roads.get(roadNum).countLights(); j++) {
            for (int t = 0; t < NUMLIGHTS; t++) {
                if (roads.get(roadNum).getTrafficLight(t) != null)
                    if (roads.get(roadNum).getTrafficLight(t).isStatus())
                        // checks for green light or no light
                        roads.get(roadNum).getVehicle(vehicleNum, LANE).drive();
                    else {
                        if ((roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() <=
                                //checks if car needs to stop before light
                                roads.get(roadNum).getTrafficLight(t).getPosition()) &&
                                (roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() +
                                        roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed() >=
                                        roads.get(roadNum).getTrafficLight(t).getPosition())) {
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).stop();
                        } else
                            roads.get(roadNum).getVehicle(vehicleNum, LANE).drive();
                    }
            }
        }

    }

    void runSimulation() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int roadNum = 0; roadNum < roads.size(); roadNum++) { //updates all roads
                    for (int vehicleNum = 0; vehicleNum < NUMCARS; vehicleNum++) {
                        if (roads.get(roadNum).getVehicle(vehicleNum, LANE) != null) { //checks for vehicles
                            System.out.printf("Model.Vehicle: %d | Road: %d | Position: %d\n",
                                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getVehicleNum(), roadNum,
                                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition());
                            if (roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed() ==
                                    roads.get(roadNum).getVehicle(vehicleNum, LANE).getMAXSPEED())
                            //checks if car needs to accelerate
                            {
                                if (roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() +
                                        roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed()
                                        > roads.get(roadNum).getLength()) //checks if car needs to swap roads
                                    changeRoads(roadNum, vehicleNum);
                            } else {
                                if (roads.get(roadNum).getVehicle(vehicleNum, LANE).getPosition() +
                                        roads.get(roadNum).getVehicle(vehicleNum, LANE).getSpeed() +
                                        roads.get(roadNum).getVehicle(vehicleNum, LANE).getAcceleration()
                                        > roads.get(roadNum).getLength())
                                    //checks if car needs to swap roads + accelerate
                                    changeRoadsAccelerating(roadNum, vehicleNum);
                            }
                        }
                        if (roads.get(roadNum).getVehicle(vehicleNum, LANE) != null) {
                            //checks for vehicle as one may have been removed
                            if (roads.get(roadNum).countLights() != 0)
                                checkForLight(roadNum, vehicleNum);
                            else
                                roads.get(roadNum).getVehicle(vehicleNum, LANE).drive();
                        }
                    }
                    if (time % 25 == 0)
                        toggleLights(roadNum);
                }
                time++; //updates timer after all cars are moved
                if (currentCars == 0) {
                    System.out.println("We have run out of vehicles!");
                    System.exit(0);
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}*/
}
