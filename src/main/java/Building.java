import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Building {

    private final Map<Integer, List<Person>> floorsWithPeople;
    private final Elevator elevator;
    private int currentStep;

    public Building() {
        int amountOfFloors = Util.generateNumber(5, 20);
        floorsWithPeople = floorsInit(amountOfFloors);
        elevator = elevatorInit(amountOfFloors);
        currentStep = 0;
        drawConsoleElevator();
    }

    public void moveElevator() {
        int currentFloor = elevator.nextFloor();
        List<Person> peopleOnTheFloor = floorsWithPeople.get(currentFloor);
        elevator.leavePeopleFromElevator(peopleOnTheFloor);
        elevator.addPeopleToElevator(peopleOnTheFloor);
        drawConsoleElevator();
    }

    private Map<Integer, List<Person>> floorsInit(int amountOfFloors) {
        return IntStream.rangeClosed(2, amountOfFloors)
                .boxed()
                .collect(Collectors.toMap(
                        key -> key,
                        value -> peopleOnTheFloorInit(amountOfFloors, value)));
    }

    private List<Person> peopleOnTheFloorInit(int amountOfFloors, int currentFloor) {
        int amountOfPeople = Util.generateNumber(0, 10);
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < amountOfPeople; i++) {
            Person person = new Person();
            person.setFloorAim(amountOfFloors, currentFloor);
            people.add(person);
        }
        return people;
    }

    private Elevator elevatorInit(int amountOfFloors) {
        List<Person> firstFloor = peopleOnTheFloorInit(amountOfFloors, 1);
        Elevator elevator = new Elevator(amountOfFloors);
        elevator.addPeopleToElevator(firstFloor);
        floorsWithPeople.put(1, firstFloor);
        return elevator;
    }

    private void drawConsoleElevator() {
        currentStep++;
        String drawStep = String.format("%11s *** STEP #%2d ***", "", currentStep);
        System.out.println(drawStep);
        for (int i = floorsWithPeople.size(); i >= 1; i--) {
            String drawPeopleInTheElevator;
            if (elevator.getCurrentFloor() == i) {
                drawPeopleInTheElevator = drawConsolePeopleInTheElevator();
            } else {
                drawPeopleInTheElevator = String.format("-|%18s|-", "");
            }
            List<Person> peopleOnTheFloor = floorsWithPeople.get(i);
            String drawPeopleOnTheFloor = drawPeopleWithFloorAim(peopleOnTheFloor);
            String drawFloor = String.format("Floor #%2d%s%s", i, drawPeopleInTheElevator, drawPeopleOnTheFloor);
            System.out.println(drawFloor);
        }
    }

    private String drawConsolePeopleInTheElevator() {
        List<Person> people = elevator.getPeopleInTheElevator();
        String peopleInTheElevator = String.format("-|^ %15s^|-", drawPeopleWithFloorAim(people));
        if (!elevator.getElevatorDirection()) {
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