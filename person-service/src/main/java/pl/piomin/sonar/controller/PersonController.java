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

import pl.piomin.sonar.exception.AuthenticationException;
import pl.piomin.sonar.exception.EntityNotFoundException;
import pl.piomin.sonar.exception.InvalidEntityException;
import pl.piomin.sonar.model.Person;
import pl.piomin.sonar.model.PersonCategory;
import pl.piomin.sonar.model.data.PersonRepository;
import pl.piomin.sonar.model.data.UserRepository;
import pl.piomin.sonar.service.AuthorizationService;

@RestController
public class PersonController {

	private static final Logger LOGGER = Logger.getLogger(PersonController.class.getName());

	private static final int THRESHOLD_KID = 11;
	private static final int THRESHOLD_TEENAGER = 18;
	private static final int THRESHOLD_GROWN = 60;
	
	@Autowired
	AuthorizationService authService;
	@Autowired
	PersonRepository repository;
	@Autowired
	UserRepository userRepository;

	@GetMapping
	public Set<Person> findAll(@RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info("Person.findAll");
		authService.authorize(auth);
		return repository.findAll();
	}

	@GetMapping("/person/lastName/{lastName}")
	public Set<Person> findByLastName(@PathVariable("lastName") String lastName,
			@RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info(() -> "Person.findByLastName: " + lastName);
		authService.authorize(auth);
		return repository.findByLastName(lastName);
	}

	@GetMapping("/person/name/{lastName}/{firstName}")
	public Set<Person> findByName(@PathVariable("lastName") String lastName,
			@PathVariable("firstName") String firstName, @RequestHeader("Authorization") String auth)
			throws AuthenticationException {
		LOGGER.info(() -> "Person.findByName: " + lastName + ", " + firstName);
		authService.authorize(auth);
		return repository.findByName(lastName, firstName);
	}

	@GetMapping("/person/{id}")
	public Person findById(@PathVariable("id") Integer id, @RequestHeader("Authorization") String auth)
			throws AuthenticationException, EntityNotFoundException {
		LOGGER.info(() -> "Person.findById: " + id);
		authService.authorize(auth);
		Person p = repository.findById(id);
		if (p != null) {
			final int years = (int) (System.currentTimeMillis() - p.getBirthDate().getTime())
					/ (1000 * 60 * 60 * 24 * 365);
			if (years < THRESHOLD_KID)
				p.setCategory(PersonCategory.KID);
			else if (years < THRESHOLD_TEENAGER)
				p.setCategory(PersonCategory.TEENAGER);
			else if (years < THRESHOLD_GROWN)
				p.setCategory(PersonCategory.GROWN);
			else
				p.setCategory(PersonCategory.PENSIONARY);
			return p;
		} else {
			throw new EntityNotFoundException("Person " + id + "nof found");
		}
	}

	@PostMapping("/person")
	public Person add(Person person, @RequestHeader("Authorization") String auth)
			throws InvalidEntityException, AuthenticationException {
		LOGGER.info(() -> "Person.add: " + person);
		authService.authorize(auth);
		return repository.add(person);
	}

	@PutMapping("/person")
	public Person update(Person person, @RequestHeader("Authorization") String auth)
			throws InvalidEntityException, AuthenticationException {
		LOGGER.info(() -> "Person.update: " + person);
		authService.authorize(auth);
		return repository.update(person);
	}

	@DeleteMapping("/person")
	public void remove(Person person, @RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info(() -> "Person.remove: " + person);
		authService.authorize(auth);
		repository.remove(person);
	}

}
