import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    private final int NUMROADS = 10, NUMLIGHTS = 3, NUMCARS = 2, MAXSPEED = 3;
    private int currentCars = NUMCARS;
    private int time;
    private ArrayList<RoadStraight> roads = new ArrayList<>();//array storing roads
    private ArrayList<Integer> lightsRoads = new ArrayList<>();//array with random values to assign roads for lights to
    // be on
    private ArrayList<Integer> carsRoads = new ArrayList<>();//array with random values to assign roads for cars to
    // be on
    private Random random = new Random();

    void createSimulation() {
        for (int i = 0; i < NUMLIGHTS; i++) { //randomly selects light road
            int lightsRoad = random.nextInt(NUMROADS);
            lightsRoads.add(lightsRoad);
        }
        for (int c = 0; c < NUMCARS; c++) { //randomly selects cars road
            int carsRoad = random.nextInt(NUMROADS / 2);
            carsRoads.add(carsRoad);
        }

        for (int i = 0; i < NUMROADS; i++) {
            RoadStraight roadStraight = new RoadStraight();
            for (int c = 0; c < NUMCARS; c++) { // creates cars
                if (roads.size() == carsRoads.get(c)) {
                    int carPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createVehicle("Car", carPosition, c, 1, 0, 1, 2);
                    System.out.println("Car " + c + " created at position " + carPosition + " on road " + i);
                }
            }
            for (int j = 0; j < NUMLIGHTS; j++) { // creates a traffic light on a road
                if (roads.size() == lightsRoads.get(j)) { //checks which road to create a light on
                    int lightsPosition = random.nextInt(roadStraight.getLength());
                    roadStraight.createTrafficLight(j, lightsPosition);
                    System.out.println("Light " + j + " created on road " + i + " at position " + lightsPosition);
                }
            }
            roads.add(roadStraight);
        }
    }

    private void changeRoads(int road, int vehicle) {
        if (road + 1 == NUMROADS) {
            System.out.println("We have run out of road for vehicle " + vehicle);
            roads.get(road).destroyVehicle(vehicle);
            currentCars--;
        } else {
            roads.get(road + 1).createVehicle("Car",
                    (roads.get(road).getVehicle(vehicle).getPosition() +
                            roads.get(road).getVehicle(vehicle).getSpeed()) % roads.get(road).getLength(),
                    roads.get(road).getVehicle(vehicle).getVehicleNum(), 1, 3, 1, 2); //Creates car on next road
            roads.get(road + 1).getVehicle(vehicle).setSpeed(roads.get(road).getVehicle(vehicle).getSpeed());
            //Sets new cars speed
            roads.get(road).destroyVehicle(vehicle); //Removes old car from old road
        }
    }

    private void changeRoadsAccelerating(int road, int vehicle) {
        if (road + 1 == NUMROADS) {
            System.out.println("We have run out of road for vehicle " + vehicle);
            roads.get(road).destroyVehicle(vehicle);
            currentCars--;

        } else {
            roads.get(road + 1).createVehicle("Car",
                    (roads.get(road).getVehicle(vehicle).getPosition() +
                            roads.get(road).getVehicle(vehicle).getSpeed() +
                            roads.get(road).getVehicle(vehicle).getAcceleration()) %
                            roads.get(road).getLength(),
                    roads.get(road).getVehicle(vehicle).getVehicleNum(), 1, roads.get(road).getVehicle(vehicle).getSpeed() +
                            roads.get(road).getVehicle(vehicle).getAcceleration(), 1, 2); //Creates car on next road
            roads.get(road).destroyVehicle(vehicle); //Removes old car from road
        }
    }

    private void toggleLights(int road) {
        if (roads.get(road).countLights() != 0)
            for (int t = 0; t < NUMLIGHTS; t++) {
                if (roads.get(road).getTrafficLight(t) != null) {
                    roads.get(road).getTrafficLight(t).toggleColour();
                    System.out.println("Light " + t + " on road " + road + " At position " +
                            roads.get(road).getTrafficLight(t).getPosition() + " toggled");
                }
            }
    }

    private void checkForLight(int road, int vehicle) {
        for (int j = 0; j < roads.get(road).countLights(); j++) {
            for (int t = 0; t < NUMLIGHTS; t++) {
                if (roads.get(road).getTrafficLight(t) != null)
                    if (roads.get(road).getTrafficLight(t).isStatus())
                        // checks for green light or no light
                        roads.get(road).getVehicle(vehicle).drive(MAXSPEED);
                    else {
                        if ((roads.get(road).getVehicle(vehicle).getPosition() <=
                                //checks if car needs to stop before light
                                roads.get(road).getTrafficLight(t).getPosition()) &&
                                (roads.get(road).getVehicle(vehicle).getPosition() +
                                        roads.get(road).getVehicle(vehicle).getSpeed() >=
                                        roads.get(road).getTrafficLight(t).getPosition())) {
                            roads.get(road).getVehicle(vehicle).stop();
                        } else
                            roads.get(road).getVehicle(vehicle).drive(MAXSPEED);
                    }
            }
        }

    }

    void runSimulation() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < roads.size(); i++) { //updates all roads
                    for (int c = 0; c < NUMCARS; c++) {
                        if (roads.get(i).getVehicle(c) != null) { //checks for vehicles
                            System.out.println("Vehicle: " + roads.get(i).getVehicle(c).getVehicleNum() + " Road: " + i +
                                    " Position: " + roads.get(i).getVehicle(c).getPosition());
                            if (roads.get(i).getVehicle(c).getSpeed() == MAXSPEED) //checks if car needs to accelerate
                            {
                                if (roads.get(i).getVehicle(c).getPosition() + roads.get(i).getVehicle(c).getSpeed()
                                        > roads.get(i).getLength()) //checks if car needs to swap roads
                                    changeRoads(i, c);
                            } else {
                                if (roads.get(i).getVehicle(c).getPosition() + roads.get(i).getVehicle(c).getSpeed() +
                                        roads.get(i).getVehicle(c).getAcceleration()
                                        > roads.get(i).getLength()) //checks if car needs to swap roads + accelerate
                                    changeRoadsAccelerating(i, c);
                            }
                        }
                        if (roads.get(i).getVehicle(c) != null) { //checks for vehicle as one may have been removed
                            if (roads.get(i).countLights() != 0)
                                checkForLight(i, c);
                            else
                                roads.get(i).getVehicle(c).drive(MAXSPEED);
                        }
                    }
                    if (time % 25 == 0)
                        toggleLights(i);
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
}