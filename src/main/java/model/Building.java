package model;

import service.ElevatorService;

import java.util.List;
import java.util.Map;

public class Building {

    private Map<Integer, List<Person>> floorsWithPeople;
    private ElevatorService elevatorService;

    public Map<Integer, List<Person>> getFloorsWithPeople() {
        return floorsWithPeople;
    }

    public void setFloorsWithPeople(Map<Integer, List<Person>> floorsWithPeople) {
        this.floorsWithPeople = floorsWithPeople;
    }

    public ElevatorService getElevatorService() {
        return elevatorService;
    }

    public void setElevatorService(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @Override
    public String toString() {
        return "Building {" +
                "floorsWithPeople = " + floorsWithPeople +
                ", elevatorService = " + elevatorService +
                '}';
    }
}