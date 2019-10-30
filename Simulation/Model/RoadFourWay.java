package Model;

import java.util.ArrayList;

public class RoadFourWay extends Road {

    final int NUMLANES = 8;
    int length;
    boolean northConnection = true;
    boolean eastConnection = true;
    boolean southConnection = true;
    boolean westConnection = true;
    private ArrayList<ArrayList<Vehicle>> vehiclesLanes = new ArrayList<>();

    public RoadFourWay(int length) {
        this.length = length;
        for (int i = 0; i < NUMLANES; i++) {
            vehiclesLanes.add(new ArrayList<Vehicle>());
        }
    }

    @Override
    public void createVehicle(String type, int position, int vehicleNum, int speed, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);

    }

    @Override
    public void destroyVehicle(int vehicleNum, int lane) {
        for (int i = 0; i < vehiclesLanes.get(lane).size(); i++)
            if (vehiclesLanes.get(lane).get(i).getVehicleNum() == vehicleNum) //removes car with matching number
                vehiclesLanes.get(lane).remove(i);
    }

    @Override
    public Vehicle getVehicle(int vehicleNum, int lane) {
        if (vehiclesLanes.get(lane).isEmpty())
            return null;
        else
            for (Vehicle value : vehiclesLanes.get(lane)) { //cycles through all vehicles
                if (value.getVehicleNum() == vehicleNum) //gets car with matching number
                    return value;
            }
        return null;
    }
}
