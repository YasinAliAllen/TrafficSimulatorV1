package Controller;

import Model.Road;
import Model.RoadFourWay;
import Model.RoadStraight;
import Model.RoadThreeWay;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    ArrayList<ArrayList<Road>> roads = new ArrayList<>();//array storing roads
    final int LENGTH = 20;
    private int numVehicles = 0;
    private int numCars = 0;
    private int numMotorbikes = 0;
    private int numBusses = 0;
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
        this.numCars = cars;
        this.numMotorbikes = motorbikes;
        this.numBusses = busses;
        this.numVehicles = cars + motorbikes + busses;
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


    private void changeRoads(int roadNum, int vehicleNum) {
        if (roadNum + 1 == NUMROADS) {
            System.out.println("We have run out of road for vehicle " + vehicleNum);
            roads.get(roadNum).destroyVehicle(vehicleNum, LANE);
            numCars--;
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
            numCars--;

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
                for (int rowNum = 0; rowNum < roads.size(); rowNum++) { //updates all roads
                    for (int roadNum = 0; roadNum < roads.get(rowNum).size(); roadNum++) {
                        for (int vehicleNum = 0; vehicleNum < numVehicles; vehicleNum++) {
                            for (int laneNum = 0; laneNum < roads.get(rowNum).get(roadNum).getLanes().size(); laneNum++) {
                            }
                            if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, LANE) != null) { //checks for vehicles
                                System.out.printf("Model.Vehicle: %d | Road: %d | Position: %d\n",
                                        roads.get(rowNum).getVehicle(vehicleNum, LANE).getVehicleNum(), rowNum,
                                        roads.get(rowNum).getVehicle(vehicleNum, LANE).getPosition());
                                if (roads.get(rowNum).getVehicle(vehicleNum, LANE).getSpeed() ==
                                        roads.get(rowNum).getVehicle(vehicleNum, LANE).getMAXSPEED())
                                //checks if car needs to accelerate
                                {
                                    if (roads.get(rowNum).getVehicle(vehicleNum, LANE).getPosition() +
                                            roads.get(rowNum).getVehicle(vehicleNum, LANE).getSpeed()
                                            > roads.get(rowNum).getLength()) //checks if car needs to swap roads
                                        changeRoads(rowNum, vehicleNum);
                                } else {
                                    if (roads.get(rowNum).getVehicle(vehicleNum, LANE).getPosition() +
                                            roads.get(rowNum).getVehicle(vehicleNum, LANE).getSpeed() +
                                            roads.get(rowNum).getVehicle(vehicleNum, LANE).getAcceleration()
                                            > roads.get(rowNum).getLength())
                                        //checks if car needs to swap roads + accelerate
                                        changeRoadsAccelerating(rowNum, vehicleNum);
                                }
                            }
                            if (roads.get(rowNum).getVehicle(vehicleNum, LANE) != null) {
                                //checks for vehicle as one may have been removed
                                if (roads.get(rowNum).countLights() != 0)
                                    checkForLight(rowNum, vehicleNum);
                                else
                                    roads.get(rowNum).getVehicle(vehicleNum, LANE).drive();
                            }
                        }
                        if (time % 25 == 0)
                            toggleLights(rowNum);
                    }
                }
                time++; //updates timer after all cars are moved
                if (numCars == 0) {
                    System.out.println("We have run out of vehicles!");
                    System.exit(0);
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
}
