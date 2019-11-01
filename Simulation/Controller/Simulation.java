package Controller;

import Model.Road;
import Model.RoadFourWay;
import Model.RoadStraight;
import Model.RoadThreeWay;

import java.util.*;

public class Simulation {
    ArrayList<ArrayList<Road>> roads = new ArrayList<>();//array storing roads
    final int LENGTH = 20;
    Random random = new Random();
    Map<String, Integer> currentVehicles = new HashMap<>();
    private int currentVehicle = 0;
    private int numSpawners = 0;
    private int chosenSpawner = 0;
    private int currentSpawner = 0;
    private int currentCars = 0;
    private int currentMotorbikes = 0;
    private int currentBusses = 0;
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
        currentVehicles.put("Cars", currentCars);
        currentVehicles.put("Motorbike", currentMotorbikes);
        currentVehicles.put("Bus", currentBusses);
        this.numSpawners = 0;
        this.numCars = cars;
        this.numMotorbikes = motorbikes;
        this.numBusses = busses;
        this.numVehicles = cars + motorbikes + busses;
        this.roads = new ArrayList<ArrayList<Road>>();//array storing roads
        for (int i = 0; i < data.size(); i++) {
            roads.add(new ArrayList<Road>());
            for (int j = 0; j < data.get(i).size(); j++) {
                String roadType = data.get(i).get(j).split(",")[0];
                int rotation = Integer.parseInt(data.get(i).get(j).split(",")[1]);
                boolean isSpawner = Boolean.parseBoolean(data.get(i).get(j).split(",")[2]);
                if (isSpawner) {
                    numSpawners++;
                }
                roads.get(i).add(roadBuilder(roadType, rotation, isSpawner));
            }
        }
        chosenSpawner = random.nextInt(numSpawners);
    }


    private void changeRoadStraight(int rowNum, int roadNum, int laneNum, int vehicleNum, String vehicleType) {
        if (roads.get(rowNum).get(roadNum).hasNorthConnection() || roads.get(rowNum).get(roadNum).hasSouthConnection()) {
            switch (laneNum) {
                case 0:
                    if (rowNum == 0) { //despawn vehicle
                        System.out.println("We have run out of road for vehicle " + vehicleNum);
                        roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum);
                        currentVehicle = vehicleNum;
                        currentCars--;
                        spawnVehicles(vehicleType);
                    } else {
                        roads.get(rowNum - 1).get(roadNum).createVehicle(vehicleType,
                                (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed())
                                        % roads.get(rowNum).get(roadNum).getLength(),
                                roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleNum(),
                                roads.get(rowNum).get(roadNum).getSpeed(laneNum, vehicleNum), laneNum); //Creates car on next road
                        roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum); //Removes old car from old road
                    }
                    break;
                case 1:
                    if (rowNum + 1 == roads.size()) { //despawn vehicle
                        System.out.println("We have run out of road for vehicle " + vehicleNum);
                        roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum);
                        currentVehicle = vehicleNum;
                        currentCars--;
                    } else {
                        roads.get(rowNum + 1).get(roadNum).createVehicle(vehicleType,
                                (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed()) % roads.get(rowNum).get(roadNum).getLength(),
                                roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleNum(),
                                roads.get(rowNum).get(roadNum).getSpeed(laneNum, vehicleNum), laneNum); //Creates car on next road
                        roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum); //Removes old car from old road
                    }
                    break;
            }
        } else if (roads.get(rowNum).get(roadNum).hasEastConnection() || roads.get(rowNum).get(roadNum).hasWestConnection()) {

        }

        /*if (roadNum + 1 == roads.get(rowNum).size()) {
            System.out.println("We have run out of road for vehicle " + vehicleNum);
            roads.get(rowNum).get(vehicleNum).destroyVehicle(vehicleNum, laneNum);
            numCars--;
        } else {
            roads.get(rowNum).get(roadNum + 1).createVehicle("Model.Car",
                    (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed()) % roads.get(rowNum).get(roadNum).getLength(),
                    roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleNum(),
                    1, laneNum); //Creates car on next road
            roads.get(rowNum).get(roadNum + 1)
                    .getVehicle(vehicleNum, laneNum).setSpeed(roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed());
            //Sets new cars speed
            roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum); //Removes old car from old road
        }*/
    }

    private void changeRoadsAccelerating(int rowNum, int roadNum, int laneNum, int vehicleNum) {
        if (roadNum + 1 == roads.get(rowNum).size()) {
            System.out.println("We have run out of road for vehicle " + vehicleNum);
            roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum);
            numCars--;

        } else {
            roads.get(rowNum).get(roadNum + 1).createVehicle("Car",
                    (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed() +
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getAcceleration()) %
                            roads.get(rowNum).get(roadNum).getLength(),
                    roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleNum(), 1,
                    roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed() +
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getAcceleration()); //Creates car on next road
            roads.get(rowNum).get(roadNum).destroyVehicle(vehicleNum, laneNum); //Removes old car from road
        }
    }

    private void toggleLights(int rowNum, int roadNum) {
        if (roads.get(rowNum).get(roadNum) != null) {
            if (roads.get(rowNum).get(roadNum).countLights() != 0)
                for (int t = 0; t < roads.get(rowNum).get(roadNum).getLength(); t++) {
                    if (roads.get(rowNum).get(roadNum).getTrafficLight(t) != null) {
                        roads.get(rowNum).get(roadNum).getTrafficLight(t).toggleColour();
                        System.out.printf("Light %d on road %d at position %d toggled.\n", t, roadNum,
                                roads.get(rowNum).get(roadNum).getTrafficLight(t).getPosition());
                    }
                }
        }
    }

    private void checkForLight(int rowNum, int roadNum, int vehicleNum, int laneNum) {
        for (int j = 0; j < roads.get(rowNum).get(roadNum).countLights(); j++) {
            for (int t = 0; t < roads.get(rowNum).get(rowNum).getLength(); t++) {
                if (roads.get(rowNum).get(roadNum).getTrafficLight(t) != null)
                    if (roads.get(rowNum).get(roadNum).getTrafficLight(t).isStatus())
                        // checks for green light or no light
                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).drive();
                    else {
                        if ((roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() <=
                                //checks if car needs to stop before light
                                roads.get(rowNum).get(roadNum).getTrafficLight(t).getPosition()) &&
                                (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed() >=
                                        roads.get(rowNum).get(roadNum).getTrafficLight(t).getPosition())) {
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).stop();
                        } else
                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).drive();
                    }
            }
        }

    }

    //randomly spawns vehicles until the maximum of that vehicle type is spawned
    void spawnVehicles(String vehicleType) {
        currentSpawner = 0;
        for (int rowNum = 0; rowNum < roads.size(); rowNum++) {
            for (int roadNum = 0; roadNum < roads.get(rowNum).size(); roadNum++) {
                if (roads.get(rowNum).get(roadNum) != null) {
                    if (roads.get(rowNum).get(roadNum).isSpawner()) {
                        if (currentSpawner == chosenSpawner) {
                            if (rowNum == 0 && roads.get(rowNum).get(roadNum).hasNorthConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 1);
                            } else if (rowNum == 0 && roadNum == roads.get(rowNum).size() - 1
                                    && roads.get(rowNum).get(roadNum).hasEastConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 1);
                            } else if (rowNum == 0 && roadNum == 0
                                    && roads.get(rowNum).get(roadNum).hasWestConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 0);
                            } else if (rowNum == roads.size() - 1 &&
                                    roads.get(rowNum).get(roadNum).hasSouthConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 0);
                            } else if (rowNum == roads.size() - 1 && roadNum == 0 &&
                                    roads.get(rowNum).get(roadNum).hasWestConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 0);
                            } else if (rowNum == roads.size() - 1 && roadNum == roads.get(rowNum).size() - 1 &&
                                    roads.get(rowNum).get(roadNum).hasEastConnection()) {
                                roads.get(rowNum).get(roadNum).createVehicle(vehicleType,
                                        0, currentVehicle, 0, 1);
                            }
                            currentVehicle++;
/*                            int tempValue = currentVehicles.get(vehicleType) + 1;
                            currentVehicles.replace(vehicleType, tempValue);*/
                            switch (vehicleType) {
                                case "Car":
                                    currentCars++;
                                    break;
                                case "Motorbike":
                                    currentMotorbikes++;
                                    break;
                                case "Bus":
                                    currentBusses++;
                                    break;
                            }
                            chosenSpawner = random.nextInt(numSpawners);

                            if (vehicleType.equals("Car")) {
                                if (currentCars == numCars) {
                                    break;
                                }
                            } else if (vehicleType.equals("Motorbike")) {
                                if (currentMotorbikes == numMotorbikes) {
                                    break;
                                }
                            } else if (vehicleType.equals("Bus")) {
                                if (currentBusses == numBusses) {
                                    break;
                                }
                            }

                        }
                        currentSpawner++;
                    }
                }
            }
            if (vehicleType.equals("Car")) {
                if (currentCars == numCars) {
                    break;
                }
            } else if (vehicleType.equals("Motorbike")) {
                if (currentMotorbikes == numMotorbikes) {
                    break;
                }
            } else if (vehicleType.equals("Bus")) {
                if (currentBusses == numBusses) {
                    break;
                }
            }
        }
    }

    void runSimulation() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (currentCars != numCars) {
                    chosenSpawner = random.nextInt(numSpawners);
                    while (currentCars < numCars) {
                        spawnVehicles("Car");
                    }
                } else if (currentMotorbikes != numMotorbikes) {
                    chosenSpawner = random.nextInt(numSpawners);
                    while (currentMotorbikes < numMotorbikes) {
                        spawnVehicles("Motorbike");
                    }
                } else if (currentBusses != numBusses) {
                    chosenSpawner = random.nextInt(numSpawners);
                    while (currentBusses < numBusses) {
                        spawnVehicles("Bus");
                    }
                } else
                    for (int rowNum = 0; rowNum < roads.size(); rowNum++) { //updates all roads
                        for (int roadNum = 0; roadNum < roads.get(rowNum).size(); roadNum++) {
                            if (roads.get(rowNum).get(roadNum) != null) {
                                if (roads.get(rowNum).get(roadNum).getLanes() != null)
                                    for (int laneNum = 0; laneNum < roads.get(rowNum).get(roadNum).getLanes().size(); laneNum++) {
                                        for (int vehicleNum = 0; vehicleNum < numVehicles; vehicleNum++) {
                                            if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum) != null) { //checks for vehicles
                                                System.out.printf("%s: %d | Row %d | Road: %d | Position: %d\n",
                                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleType(),
                                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleNum(), rowNum, roadNum,
                                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition());
                                                if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed() ==
                                                        roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getMAXSPEED())
                                                //checks if car needs to accelerate
                                                {
                                                    if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                                                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed()
                                                            > roads.get(rowNum).get(roadNum).getLength()) //checks if car needs to swap roads
                                                        changeRoadStraight(rowNum, roadNum, laneNum, vehicleNum,
                                                                roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getVehicleType());
                                                } else {
                                                    if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getPosition() +
                                                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getSpeed() +
                                                            roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).getAcceleration()
                                                            > roads.get(rowNum).get(roadNum).getLength())
                                                        //checks if car needs to swap roads + accelerate
                                                        changeRoadsAccelerating(rowNum, roadNum, laneNum, vehicleNum);
                                                }
                                            }
                                            if (roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum) != null) {
                                                //checks for vehicle as one may have been removed
                                                if (roads.get(rowNum).get(roadNum).countLights() != 0)
                                                    checkForLight(rowNum, roadNum, vehicleNum, laneNum);
                                                else
                                                    roads.get(rowNum).get(roadNum).getVehicle(vehicleNum, laneNum).drive();
                                            }
                                        }
                                    }


                                if (time % 25 == 0)
                                    toggleLights(rowNum, roadNum);

                            }
                        }
                        time++; //updates timer after all cars are moved
                        if (numCars == 0) {
                            System.out.println("We have run out of vehicles!");
                            System.exit(0);
                        }
                    }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 10);
    }
}