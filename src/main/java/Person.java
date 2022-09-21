public class Person {

    private int floorAim;

    public int getFloorAim() {
        return floorAim;
    }

    public void setFloorAim(int amountOfFloors, int currentFloor) {
        floorAim = currentFloor;
        while (floorAim == currentFloor) {
            floorAim = Util.generateNumber(1, amountOfFloors);
        }
    }

    @Override
    public String toString() {
        return "Person {" +
                "floorAim = " + floorAim +
                '}';
    }
}