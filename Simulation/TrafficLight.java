public class TrafficLight {
    private int position;
    boolean status; //true = green | false = red

    public void toggleColour() {
        status = !status;
    }

    public int getPosition() {
        return position;
    }

    public TrafficLight(int position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }
}
