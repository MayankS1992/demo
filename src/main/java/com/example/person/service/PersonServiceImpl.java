package com.example.person.service;

import com.example.person.domain.Person;
import com.example.person.exception.PersonNotFoundException;
import com.example.person.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersonList() {
        log.info("Getting the list of person object");
        List<Person> personList = personRepository.findAll();
        if(personList.size()==0)
            throw new PersonNotFoundException("No Data Found or Empty List");
        else
            return personList;
    }

    @Override
    public void deletePerson(Long id) {
        try {
            log.info("deleting person with id {}", id);
            personRepository
                    .findById(id)
                    .orElseThrow(()->new PersonNotFoundException("Person Not Found"));
            personRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error Encountered"+ e);
        }
    }

    @Override
    public long getPersonCount() {
        log.info("Getting the count of person");
        return personRepository.count();
    }

    @Override
    public void updatePerson(Person person) {
        log.info("Updating person details request for: "+ person.getId());
        personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(long id) {
        log.info("Retrieving person details for: "+ id);
        return personRepository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
