public class TrafficLight {
    int position;
    boolean status; //true = green | false = red

    public void toggleColour() {
        status = !status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }
}
