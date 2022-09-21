package service;

import model.Elevator;
import model.Person;

import java.util.List;

public interface ElevatorService {

    int nextFloor();

    void addPeopleToElevator(List<Person> peopleOnTheFloor);

    void leavePeopleFromElevator(List<Person> peopleOnTheFloor);

    Elevator getElevator();
}