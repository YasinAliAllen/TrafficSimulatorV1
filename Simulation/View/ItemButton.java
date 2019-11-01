package View;

import javax.swing.*;

public class ItemButton extends JButton {
    private int rotations;
    private String roadType = "";
    private boolean northConnection = false;
    private boolean eastConnection = false;
    private boolean southConnection = false;
    private boolean westConnection = false;
    private boolean hasRoad = false;
    /*    private boolean hasLights = false;*/
    private boolean isEndPiece = false;
    private boolean spawner = false;

    public ItemButton() {
        setBorderPainted(false);
        setContentAreaFilled(false);
    }


    public boolean hasRoad() {
        return hasRoad;
    }

    void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    void setIsEndPiece() {
        this.isEndPiece = true;
    }

    public boolean isEndPiece() {
        return isEndPiece;
    }

    public String getRoadType() {
        return roadType;
    }

    void setRoadType(String roadType, int rotations) {
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

    void setNorthConnection() {
        this.northConnection = false;
    }

    public boolean hasEastConnection() {
        return eastConnection;
    }

    void setEastConnection() {
        this.eastConnection = false;
    }

    public boolean hasSouthConnection() {
        return southConnection;
    }

    void setSouthConnection() {
        this.southConnection = false;
    }

    public boolean hasWestConnection() {
        return westConnection;
    }

    void setWestConnection() {
        this.westConnection = false;
    }

    public boolean isSpawner() {
        return spawner;
    }

    public void setSpawner(boolean spawner) {
        this.spawner = spawner;
    }

    public int getRotations() {
        return rotations;
    }
}
