package com.example.person.service;

import com.example.person.domain.Person;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Mayank Srivastava
 */
public interface PersonService {

    /**
     * Retrieves the list of Person
     * @return List of person in record
     */
    List<Person> getPersonList();

    /**
     * Deletes person by ID
     * @param id - unique id of the person
     */
    void deletePerson(Long id);

    /**
     * Count of Person
     * @return long value - total Count of person
     */
    long getPersonCount();

    /**
     * Update a Person
     * @param person person object to be updated
     */
    void updatePerson(Person person);

    /**
     * Find a person by ID
     * @param id - unique id of person
     * @return the Person object if it exists
     */
    Optional<Person> findById(long id);

    /**
     *
     * @param person
     * @return
     */
    Person save(Person person);
}
