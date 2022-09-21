package model;

import Util.Util;
import Util.Constant;

public class Person {

    private int floorAim;

    public int getFloorAim() {
        return floorAim;
    }

    public void setFloorAim(int amountOfFloors, int currentFloor) {
        floorAim = currentFloor;
        while (floorAim == currentFloor) {
            floorAim = Util.generateNumber(Constant.FIRST_FLOOR, amountOfFloors);
        }
    }

    @Override
    public String toString() {
        return "entity.Person {" +
                "floorAim = " + floorAim +
                '}';
    }
}