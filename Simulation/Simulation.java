import java.util.ArrayList;

public class Simulation {
    int speedMax, time;
    private final int MAXSPEED = 20, STOPTIME = 100;
    private ArrayList<RoadStraight> roads = new ArrayList<>();

    void createSimulation() {
        RoadStraight roadStraight = new RoadStraight();
        roadStraight.createVehicle("Car");
        roads.add(roadStraight);
    }

    void startSimulation() {
        while (time < STOPTIME) {
            if (roads.get(0).getVehicle().getPosition() + roads.get(0).getVehicle().getSpeed() >
                    roads.get(0).getLength())
                break;
            roads.get(0).getVehicle().drive(MAXSPEED);
            System.out.println(roads.get(0).getVehicle().getPosition());
            time++;
        }
    }
}
