package Model;

import java.util.ArrayList;

public abstract class Road {

    public abstract void createVehicle(String type, int position, int vehicleNum, int speed, int lane);

    public abstract void destroyVehicle(int vehicleNum, int lane);

    public abstract Vehicle getVehicle(int vehicleNum, int lane);

    public abstract ArrayList<ArrayList<Vehicle>> getLanes();

}

