package com.alura.literalura.service;

import com.alura.literalura.model.Person;
import com.alura.literalura.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		List<Person> persons = personRepository.findAll();
		System.out.println(persons.size() + " author(s) found.");
		return persons;
	}

	public List<Person> getLivingPersons(int year) {
		List<Person> persons = personRepository.findPersonsAliveInYear(year);
		System.out.println(persons.size() + " author(s) found.");
		return persons;
	}
}

