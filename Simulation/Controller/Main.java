package Controller;

import View.ItemPanel;
import View.TrafficSimGUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        TrafficSimGUI trafficSimGUI = new TrafficSimGUI();

        trafficSimGUI.addExitActionListener(actionEvent -> System.exit(0));

        trafficSimGUI.addSimulationActionListener(actionEvent -> {
            trafficSimGUI.updateStatus("Building...");
            int spawners = 0;
            boolean roadsConnected = true;
            ArrayList<ArrayList<ItemPanel>> items = trafficSimGUI.getItems();
            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < items.get(i).size(); j++) {
                    if (i != 0 && items.get(i).get(j).hasRoad() && items.get(i).get(j).hasNorthConnection()) { //checks north to south connections
                        System.out.println("North to south");
                        if (!items.get(i - 1).get(j).hasSouthConnection()) {
                            roadsConnected = false;
                            break;
                        }
                    }
                    if (j != 0 && items.get(i).get(j).hasRoad() && items.get(i).get(j).hasWestConnection()) { //checks west to east connection
                        if (!items.get(i).get(j - 1).hasEastConnection()) {
                            roadsConnected = false;
                            break;
                        }
                    }
                    if ((i != items.size() - 1) && items.get(i).get(j).hasRoad() && items.get(i).get(j).hasSouthConnection()) {
                        if (!items.get(i + 1).get(j).hasNorthConnection()) {
                            roadsConnected = false;
                            break;
                        }
                    }
                    if ((j != items.get(i).size() - 1) &&
                            items.get(i).get(j).hasEastConnection()) {
                        if (!items.get(i).get(j + 1).hasWestConnection()) {
                            roadsConnected = false;
                        }
                    }

                    //set spawner logic, this can be turned into and ors.
                    if (i == 0 && items.get(i).get(j).isEndPiece() && items.get(i).get(j).hasRoad() &&
                            items.get(i).get(j).hasNorthConnection()) {
                        spawners++;
                        items.get(i).get(j).setSpawner(true);
                    } else if ((i == items.size() - 1) && items.get(i).get(j).isEndPiece() && items.get(i).get(j).hasRoad() &&
                            items.get(i).get(j).hasSouthConnection()) {
                        spawners++;
                        items.get(i).get(j).setSpawner(true);
                    } else if (j == 0 && items.get(i).get(j).isEndPiece() && items.get(i).get(j).hasRoad() &&
                            items.get(i).get(j).hasEastConnection()) {
                        spawners++;
                        items.get(i).get(j).setSpawner(true);
                    } else if ((j == items.get(i).size() - 1 && items.get(i).get(j).isEndPiece() && items.get(i).get(j).hasRoad() &&
                            items.get(i).get(j).hasWestConnection())) {
                        spawners++;
                        items.get(i).get(j).setSpawner(true);
                    }
                }
            }

            if (spawners > 1 && roadsConnected) {
                simulation.createSimulation(2, 2, 2);
                trafficSimGUI.updateStatus("Ready to Run!");
            } else {
                trafficSimGUI.updateStatus("Ensure all Roads are Connected");
            }
        });
        trafficSimGUI.addRunActionListener(actionEvent -> {
            simulation.runSimulation();
            trafficSimGUI.updateStatus("Running...");
        });
    }
}
