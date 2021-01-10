package com.example.person;

import com.example.person.controller.PersonController;
import com.example.person.domain.Person;
import com.example.person.repository.PersonRepository;
import com.example.person.service.PersonService;
import com.example.person.validator.PersonValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc @Slf4j
class DemoApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PersonService service;

	@Autowired
	private ObjectMapper objectMapper;

	@InjectMocks
	PersonController personController;

	@MockBean
	PersonValidator personValidator;

	private Person person1;

	@Before
	public void beforeClass() throws Exception {
		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(personController).build();
		when(personValidator.supports(any())).thenReturn(true);
		person1 = new Person("Robin","Watson");
		mvc.perform(post("/addPerson/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(person1)))
				.andExpect(status().isOk());
	}

	@Test
	void savePersonTest() throws Exception {
		List<Person> personList = Arrays.asList(person1);
		given(service.getPersonList()).willReturn(personList);
	}

	@Test
	void deletePersonTest() throws Exception {
		//Populating embedded database here
		mvc.perform(delete("/deletePerson/{id}/",1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(person1)))
				.andExpect(status().isOk());
	}

	@Test
	void updatePersonTest() throws Exception {
		person1 = new Person("Robin","Watson");
		mvc.perform(post("/addPerson/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(person1)))
				.andExpect(status().isOk());
		mvc.perform(put("/updatePerson/{id}",1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(person1)))
				.andExpect(status().isOk());
	}

	@Test
	void countTest() throws Exception {
		Assert.assertEquals(5,personRepository.count());
	}
}
