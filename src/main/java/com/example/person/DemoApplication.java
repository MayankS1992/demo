package com.example.person;

import com.example.person.domain.Person;
import com.example.person.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @Slf4j
public class DemoApplication implements CommandLineRunner {

	private PersonRepository personRepository;

	@Autowired
	public DemoApplication(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//Populating embedded database here
		log.info("Saving users. Current user count is {}.", personRepository.count());
		Person person1 = new Person("Robin","Watson");
		Person person2 = new Person("Rohan","Hayden");
		Person person5 = new Person("Amuktha","Dasi");
		Person person3 = new Person("Eoin","Morgan");
		Person person4 = new Person("Sim","Parson");

		personRepository.save(person1);
		personRepository.save(person2);
		personRepository.save(person3);
		personRepository.save(person4);
		personRepository.save(person5);

		log.info("Done saving users. Data: {}.", personRepository.findAll());
	}
}
