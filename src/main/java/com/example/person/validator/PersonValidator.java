package com.example.person.validator;

import com.example.person.domain.Person;
import com.example.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    @Autowired
    PersonService personService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "error.firstname", "Firstname is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "error.surname", "Surname is required.");
    }
}
