package service.impl;

import util.Constant;
import model.Elevator;
import model.Person;
import service.ElevatorService;

import java.util.List;
import java.util.stream.Collectors;

public class ElevatorServiceImpl implements ElevatorService {

    private final Elevator elevator;

    public ElevatorServiceImpl(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public int nextFloor() {
        int currentFloor = elevator.getCurrentFloor();
        currentFloor = elevator.getElevatorDirection() ? currentFloor + 1 : currentFloor - 1;
        if (currentFloor == elevator.getAmountOfFloors() || currentFloor == 1) {
            elevator.setElevatorDirectionIsUpSide(!elevator.getElevatorDirection());
        }
        elevator.setCurrentFloor(currentFloor);
        return currentFloor;
    }

    @Override
    public void addPeopleToElevator(List<Person> peopleOnTheFloor) {
        List<Person> peopleToGo = peopleOnTheFloor.stream()
                .filter(person ->
                        (elevator.getElevatorDirection() && person.getFloorAim() > elevator.getCurrentFloor())
                                || (!elevator.getElevatorDirection() && person.getFloorAim() < elevator.getCurrentFloor()))
                .limit(Constant.ELEVATOR_MAX_SIZE - elevator.getPeopleInTheElevator().size())
                .collect(Collectors.toList());

        elevator.getPeopleInTheElevator().addAll(peopleToGo);
        peopleOnTheFloor.removeAll(peopleToGo);

        if (elevator.getPeopleInTheElevator().isEmpty() && elevator.getCurrentFloor() != 1) {
            elevator.setElevatorDirectionIsUpSide(!elevator.getElevatorDirection());
            addPeopleToElevator(peopleOnTheFloor);
        }
    }

    @Override
    public void leavePeopleFromElevator(List<Person> peopleOnTheFloor) {
        List<Person> peopleToLeave = elevator.getPeopleInTheElevator().stream()
                .filter(person -> person.getFloorAim() == elevator.getCurrentFloor())
                .collect(Collectors.toList());

        peopleToLeave.forEach(person -> person.setFloorAim(elevator.getAmountOfFloors(), elevator.getCurrentFloor()));

        elevator.getPeopleInTheElevator().removeAll(peopleToLeave);
        peopleOnTheFloor.addAll(peopleToLeave);
    }

    @Override
    public Elevator getElevator() {
        return elevator;
    }
}