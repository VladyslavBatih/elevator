package util;

import model.Building;
import service.BuildingService;
import service.impl.BuildingServiceImpl;

public class Program {

    public static void start() {
        BuildingService buildingService = new BuildingServiceImpl(new Building());
        buildingService.init();
        for (int i = 0; i < 40; i++) {
            buildingService.moveElevator();
        }
    }

    private Program(){
    }
}