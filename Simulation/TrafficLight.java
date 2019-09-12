class TrafficLight {
    private int position;
    private boolean status; //true = green | false = red
    private int lightNum;

    TrafficLight(int lightNum, int position) {
        this.position = position;
        this.lightNum = lightNum;
    }

    void toggleColour() {
        status = !status;
    }

    int getPosition() {
        return position;
    }

    boolean isStatus() {
        return status;
    }

    int getLightNumber() {
        return lightNum;
    }
}
