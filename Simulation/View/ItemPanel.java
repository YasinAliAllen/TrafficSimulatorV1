package View;


import javax.swing.*;

public class ItemPanel extends JPanel {
    private int rotations;
    private String roadType = "";
    private boolean northConnection = false;
    private boolean eastConnection = false;
    private boolean southConnection = false;
    private boolean westConnection = false;
    private boolean hasRoad = false;
    private boolean hasLights = false;
    private boolean isEndPiece = false;
    private boolean spawner = false;
    public ItemPanel() {

    }

    public boolean hasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    public void setIsEndPiece(boolean isEndPiece) {
        this.isEndPiece = isEndPiece;
    }

    public boolean isEndPiece() {
        return isEndPiece;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType, int rotations) {
        this.roadType = roadType;
        this.rotations = rotations;
        switch (roadType) {
            case "Road Straight":
                if (rotations == 0) {
                    northConnection = false;
                    eastConnection = true;
                    southConnection = false;
                    westConnection = true;
                } else if (rotations == 1) {
                    northConnection = true;
                    eastConnection = false;
                    southConnection = true;
                    westConnection = false;
                }
                break;
            case "Road Three Way":
                if (rotations == 0) {
                    northConnection = false;
                    eastConnection = true;
                    southConnection = true;
                    westConnection = true;
                } else if (rotations == 1) {
                    northConnection = true;
                    eastConnection = true;
                    southConnection = true;
                    westConnection = false;
                } else if (rotations == 2) {
                    northConnection = true;
                    eastConnection = true;
                    southConnection = false;
                    westConnection = true;
                } else if (rotations == 3) {
                    northConnection = true;
                    eastConnection = false;
                    southConnection = true;
                    westConnection = true;
                }
                break;
            case "Road Four Way":
                northConnection = true;
                eastConnection = true;
                southConnection = true;
                westConnection = true;
                break;
        }
    }

    public boolean hasNorthConnection() {
        return northConnection;
    }

    public void setNorthConnection(boolean northConnection) {
        this.northConnection = northConnection;
    }

    public boolean hasEastConnection() {
        return eastConnection;
    }

    public void setEastConnection(boolean eastConnection) {
        this.eastConnection = eastConnection;
    }

    public boolean hasSouthConnection() {
        return southConnection;
    }

    public void setSouthConnection(boolean southConnection) {
        this.southConnection = southConnection;
    }

    public boolean hasWestConnection() {
        return westConnection;
    }

    public void setWestConnection(boolean westConnection) {
        this.westConnection = westConnection;
    }

    public boolean isSpawner() {
        return spawner;
    }

    public void setSpawner(boolean spawner) {
        this.spawner = spawner;
    }
}