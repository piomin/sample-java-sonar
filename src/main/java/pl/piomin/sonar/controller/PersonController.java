package pl.piomin.sonar.controller;

import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.sonar.exception.InvalidEntityException;
import pl.piomin.sonar.model.Person;
import pl.piomin.sonar.model.data.PersonRepository;
import pl.piomin.sonar.service.AuthorizationService;

@RestController
public class PersonController {

	Logger logger = Logger.getLogger(PersonController.class.getName());
	
	@Autowired
	AuthorizationService authService;
	@Autowired
	PersonRepository repository;
	
	@GetMapping
	public Set<Person> findAll(@RequestHeader("Authorization") String auth) {
		logger.info("Person.findAll");
		authService.authorize(auth);
		return repository.findAll();
	}
	
	@GetMapping("/person/lastName/{lastName}")
	public Set<Person> findByLastName(@PathVariable("lastName") String lastName) {
		logger.info("Person.findByLastName: " + lastName);
		return repository.findByLastName(lastName);
	}
	
	@GetMapping("/person/name/{lastName}/{firstName}")
	public Set<Person> findByName(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
		logger.info("Person.findByName: " + lastName + ", " + firstName);
		return repository.findByName(lastName, firstName);
	}
	
	@GetMapping("/person/{id}")
	public Person findById(@PathVariable("id") Integer id) {
		logger.info("Person.findById: " + id);
		return repository.findById(id);
	}
	
	@PostMapping("/person")
	public Person add(Person person) throws InvalidEntityException {
		logger.info("Person.add: " + person);
		return repository.add(person);
	}
	
	@PutMapping("/person")
	public Person update(Person person) throws InvalidEntityException {
		logger.info("Person.update: " + person);
		return repository.update(person);
	}
	
	@DeleteMapping("/person")
	public void remove(Person person) {
		logger.info("Person.remove: " + person);
		repository.remove(person);
	}
	
}
