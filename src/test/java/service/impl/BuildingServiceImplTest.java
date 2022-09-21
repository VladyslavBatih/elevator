package service.impl;

import model.Building;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.BuildingService;
import util.Constant;

public class BuildingServiceImplTest {

    static BuildingService buildingService = new BuildingServiceImpl(new Building());

    @BeforeAll
    static void init() {
        buildingService.init();
    }

    @Test
    void testInit() {
        Assertions.assertNotNull(buildingService.getBuilding().getFloorsWithPeople());

        Assertions.assertNotNull(buildingService.getBuilding().getElevatorService());
    }

    @Test
    void testMoveElevator() {
        buildingService.moveElevator();

        Assertions.assertTrue(
                Constant.ELEVATOR_MAX_SIZE
                >= buildingService.getBuilding().getElevatorService().getElevator().getPeopleInTheElevator().size());

        Assertions.assertEquals(
                Constant.SECOND_FLOOR,
                buildingService.getBuilding().getElevatorService().getElevator().getCurrentFloor());
    }
}