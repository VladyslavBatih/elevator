import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Elevator {

    private final List<Person> peopleInTheElevator;

    private final int amountOfFloors;
    private int currentFloor;
    private boolean elevatorDirectionIsUpSide;

    public Elevator(int floors) {
        peopleInTheElevator = new ArrayList<>();
        currentFloor = 1;
        amountOfFloors = floors;
        elevatorDirectionIsUpSide = true;
    }

    public int nextFloor() {
        currentFloor = elevatorDirectionIsUpSide ? currentFloor + 1 : currentFloor - 1;
        if (currentFloor == amountOfFloors || currentFloor == 1) {
            changeDirection();
        }
        return currentFloor;
    }

    public void addPeopleToElevator(List<Person> peopleOnTheFloor) {
        List<Person> peopleToGo = peopleOnTheFloor.stream()
                .filter(person ->
                        (elevatorDirectionIsUpSide && person.getFloorAim() > currentFloor)
                        || (!elevatorDirectionIsUpSide && person.getFloorAim() < currentFloor))
                .limit(5 - peopleInTheElevator.size())
                .collect(Collectors.toList());
        peopleInTheElevator.addAll(peopleToGo);
        peopleOnTheFloor.removeAll(peopleToGo);
        if (peopleInTheElevator.isEmpty() && currentFloor != 1) {
            changeDirection();
        }
    }

    public void leavePeopleFromElevator(List<Person> peopleOnTheFloor) {
        List<Person> peopleToLeave = peopleInTheElevator.stream()
                .filter(person -> person.getFloorAim() == currentFloor)
                .collect(Collectors.toList());
        peopleToLeave.forEach(person -> person.setFloorAim(amountOfFloors, currentFloor));
        peopleInTheElevator.removeAll(peopleToLeave);
        peopleOnTheFloor.addAll(peopleToLeave);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<Person> getPeopleInTheElevator() {
        return peopleInTheElevator;
    }

    public boolean getElevatorDirection() {
        return elevatorDirectionIsUpSide;
    }

    private void changeDirection() {
        elevatorDirectionIsUpSide = !elevatorDirectionIsUpSide;
    }
}