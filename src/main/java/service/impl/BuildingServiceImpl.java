package service.impl;

import util.Util;
import util.Constant;
import model.Building;
import model.Elevator;
import model.Person;
import service.BuildingService;
import service.ElevatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuildingServiceImpl implements BuildingService {

    private final Building building;
    private int currentStep;

    public BuildingServiceImpl(Building building) {
        this.building = building;
    }

    @Override
    public void init() {
        int amountOfFloors = Util.generateNumber(Constant.BUILDING_MIN_FLOOR, Constant.BUILDING_MAX_FLOOR);
        building.setFloorsWithPeople(floorsInit(amountOfFloors));
        building.setElevatorService(elevatorServiceInit(amountOfFloors));
        drawConsoleElevator();
    }

    @Override
    public void moveElevator() {
        int currentFloor = building.getElevatorService().nextFloor();
        List<Person> peopleOnTheFloor = building.getFloorsWithPeople().get(currentFloor);
        building.getElevatorService().leavePeopleFromElevator(peopleOnTheFloor);
        building.getElevatorService().addPeopleToElevator(peopleOnTheFloor);
        drawConsoleElevator();
    }

    @Override
    public Building getBuilding() {
        return building;
    }

    private Map<Integer, List<Person>> floorsInit(int amountOfFloors) {
        return IntStream.rangeClosed(Constant.SECOND_FLOOR, amountOfFloors)
                .boxed()
                .collect(Collectors.toMap(
                        key -> key,
                        value -> peopleOnTheFloorInit(amountOfFloors, value)));
    }

    private List<Person> peopleOnTheFloorInit(int amountOfFloors, int currentFloor) {
        int amountOfPeople = Util.generateNumber(Constant.FLOOR_MIN_PEOPLE, Constant.FLOOR_MAX_PEOPLE);
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < amountOfPeople; i++) {
            Person person = new Person();
            person.setFloorAim(amountOfFloors, currentFloor);
            people.add(person);
        }
        return people;
    }

    private ElevatorService elevatorServiceInit(int amountOfFloors) {
        List<Person> firstFloor = peopleOnTheFloorInit(amountOfFloors, Constant.FIRST_FLOOR);
        ElevatorService elevator = new ElevatorServiceImpl(new Elevator(amountOfFloors));
        elevator.addPeopleToElevator(firstFloor);
        building.getFloorsWithPeople().put(Constant.FIRST_FLOOR, firstFloor);
        return elevator;
    }

    private void drawConsoleElevator() {
        currentStep++;
        String drawStep = String.format("%11s *** STEP #%2d ***", "", currentStep);
        System.out.println(drawStep);
        for (int i = building.getFloorsWithPeople().size(); i >= 1; i--) {
            String drawPeopleInTheElevator;
            if (building.getElevatorService().getElevator().getCurrentFloor() == i) {
                drawPeopleInTheElevator = drawConsolePeopleInTheElevator();
            } else {
                drawPeopleInTheElevator = String.format("-|%18s|-", "");
            }
            List<Person> peopleOnTheFloor = building.getFloorsWithPeople().get(i);
            String drawPeopleOnTheFloor = drawPeopleWithFloorAim(peopleOnTheFloor);

            String drawFloor = String.format("Floor #%2d%s%s", i, drawPeopleInTheElevator, drawPeopleOnTheFloor);
            System.out.println(drawFloor);
        }
    }

    private String drawConsolePeopleInTheElevator() {
        List<Person> people = building.getElevatorService().getElevator().getPeopleInTheElevator();
        String peopleInTheElevator = String.format("-|^ %15s^|-", drawPeopleWithFloorAim(people));
        if (!building.getElevatorService().getElevator().getElevatorDirection()) {
            peopleInTheElevator = peopleInTheElevator.replace('^', 'V');
        }
        return peopleInTheElevator;
    }

    private String drawPeopleWithFloorAim(List<Person> people) {
        StringBuilder stringBuilder = new StringBuilder();
        people.forEach(person -> stringBuilder.append(person.getFloorAim()).append(" "));
        return stringBuilder.toString();
    }
}