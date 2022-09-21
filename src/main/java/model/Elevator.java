package model;

import util.Constant;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private final List<Person> peopleInTheElevator;

    private final int amountOfFloors;
    private int currentFloor;
    private boolean elevatorDirectionIsUpSide;

    public Elevator(int floors) {
        peopleInTheElevator = new ArrayList<>();
        currentFloor = Constant.FIRST_FLOOR;
        amountOfFloors = floors;
        elevatorDirectionIsUpSide = true;
    }

    public List<Person> getPeopleInTheElevator() {
        return peopleInTheElevator;
    }

    public int getAmountOfFloors() {
        return amountOfFloors;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean getElevatorDirection() {
        return elevatorDirectionIsUpSide;
    }

    public void setElevatorDirectionIsUpSide(boolean elevatorDirectionIsUpSide) {
        this.elevatorDirectionIsUpSide = elevatorDirectionIsUpSide;
    }

    @Override
    public String toString() {
        return "Elevator {" +
                "peopleInTheElevator = " + peopleInTheElevator +
                ", amountOfFloors = " + amountOfFloors +
                ", currentFloor = " + currentFloor +
                ", elevatorDirectionIsUpSide = " + elevatorDirectionIsUpSide +
                '}';
    }
}