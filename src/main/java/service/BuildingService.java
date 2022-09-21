package service;

import model.Building;

public interface BuildingService {

    void init();

    void moveElevator();

    Building getBuilding();
}