package com.example.person.controller;

import com.example.person.domain.Person;
import com.example.person.service.PersonService;
import com.example.person.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Person Controller
 * Person related Endpoints
 *
 * @Author: Mayank Srivastava
 */
@RestController
@Slf4j
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @PostMapping("/addPerson")
    public ResponseEntity<String> submit(@Valid @RequestBody Person person, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Bad Data");
        }
        personService.save(person);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Item Added " + person.getFirstname().concat(" ").concat(person.getSurname()));
    }

    @RequestMapping(value = "/listPerson", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> listPerson() {
        List<Person> personList = personService.getPersonList();
        if (personList.isEmpty()) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("totalCount", String.valueOf(personList.size()));
        return new ResponseEntity<>(personList, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable @Min(1) Long id) {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Item Deleted " + id);
    }

    @RequestMapping(value = "/personCount", method = RequestMethod.GET)
    public ResponseEntity<String> personCount() {
        long count = personService.getPersonCount();
        if (count==0) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Total count of person is" + count);
    }

    @PutMapping(value = "/updatePerson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updatePerson(@RequestBody @Valid Person person, @PathVariable long id) {
        Optional<Person> personOptional = personService.findById(id);
        if (!personOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person Not present in records" + person.getFirstname());
        person.setId(id);
        personService.updatePerson(person);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Record updated ");
    }


}
