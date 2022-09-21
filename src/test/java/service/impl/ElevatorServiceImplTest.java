package service.impl;

import model.Elevator;
import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ElevatorService;
import util.Constant;

import java.util.ArrayList;
import java.util.List;

public class ElevatorServiceImplTest {

    ElevatorService elevatorService = new ElevatorServiceImpl(new Elevator(Constant.BUILDING_MIN_FLOOR));

    @Test
    void testNextFloor() {
        elevatorService.nextFloor();

        Assertions.assertEquals(
                Constant.SECOND_FLOOR,
                elevatorService.getElevator().getCurrentFloor());

        elevatorService.nextFloor();
        elevatorService.nextFloor();

        Assertions.assertTrue(elevatorService.getElevator().getElevatorDirection());

        Assertions.assertEquals(5, elevatorService.nextFloor());
    }

    @Test
    void testAddPeopleToElevator() {
        List<Person> peopleOnTheFloor = createPeople();
        elevatorService.addPeopleToElevator(peopleOnTheFloor);

        Assertions.assertFalse(elevatorService.getElevator().getPeopleInTheElevator().isEmpty());

        Assertions.assertEquals(
                3,
                elevatorService.getElevator().getPeopleInTheElevator().size());

        Assertions.assertTrue(peopleOnTheFloor.isEmpty());
    }

    @Test
    void testLeavePeopleFromElevator() {
        List<Person> peopleOnTheFloor = createPeople();
        List<Person> peopleInTheElevator = elevatorService.getElevator().getPeopleInTheElevator();
        List<Person> people = createPeople();

        peopleInTheElevator.addAll(people);
        peopleInTheElevator.forEach(person -> person.setFloorAim(Constant.SECOND_FLOOR, Constant.FIRST_FLOOR));

        elevatorService.nextFloor();
        elevatorService.leavePeopleFromElevator(peopleOnTheFloor);

        Assertions.assertFalse(peopleOnTheFloor.isEmpty());

        Assertions.assertEquals(6, peopleOnTheFloor.size());

        Assertions.assertTrue(elevatorService.getElevator().getPeopleInTheElevator().isEmpty());
    }

    List<Person> createPeople() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setFloorAim(Constant.BUILDING_MIN_FLOOR, Constant.FIRST_FLOOR);
            people.add(person);
        }
        return people;
    }
}