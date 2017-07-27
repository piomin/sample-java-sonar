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
import pl.piomin.sonar.model.Person;
import pl.piomin.sonar.model.PersonCategory;
import pl.piomin.sonar.model.data.PersonRepository;
import pl.piomin.sonar.model.data.UserRepository;
import pl.piomin.sonar.service.AuthorizationService;

@RestController
/**
 * Main controller
 * @author minkowp
 *
 */
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
	/**
	 * Returning all persons
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	public Set<Person> findAll(@RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info("Person.findAll");
		authService.authorize(auth);
		return repository.findAll();
	}

	@GetMapping("/person/lastName/{lastName}")
	/**
	 * Returning all persons with lastName
	 * @param lastName
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	public Set<Person> findByLastName(@PathVariable("lastName") String lastName,
			@RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info(() -> "Person.findByLastName: " + lastName);
		authService.authorize(auth);
		return repository.findByLastName(lastName);
	}

	@GetMapping("/person/name/{lastName}/{firstName}")
	/**
	 * Returning all persons with lastName and firstName
	 * @param lastName
	 * @param firstName
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	public Set<Person> findByName(@PathVariable("lastName") String lastName,
			@PathVariable("firstName") String firstName, @RequestHeader("Authorization") String auth)
			throws AuthenticationException {
		LOGGER.info(() -> "Person.findByName: " + lastName + ", " + firstName);
		authService.authorize(auth);
		return repository.findByName(lastName, firstName);
	}

	@GetMapping("/person/{id}")
	/**
	 * Returning person with id
	 * @param id
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 * @throws EntityNotFoundException
	 */
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
	/**
	 * Adding new person
	 * @param person
	 * @param auth
	 * @return
	 * @throws InvalidEntityException
	 * @throws AuthenticationException
	 */
	public Person add(Person person, @RequestHeader("Authorization") String auth)
			throws AuthenticationException {
		LOGGER.info(() -> "Person.add: " + person);
		authService.authorize(auth);
		return repository.add(person);
	}

	@PutMapping("/person")
	/**
	 * Updating existing person
	 * @param person
	 * @param auth
	 * @return
	 * @throws InvalidEntityException
	 * @throws AuthenticationException
	 */
	public Person update(Person person, @RequestHeader("Authorization") String auth)
			throws AuthenticationException {
		LOGGER.info(() -> "Person.update: " + person);
		authService.authorize(auth);
		return repository.update(person);
	}

	@DeleteMapping("/person")
	/**
	 * Removing person from repository
	 * @param person
	 * @param auth
	 * @throws AuthenticationException
	 */
	public void remove(Person person, @RequestHeader("Authorization") String auth) throws AuthenticationException {
		LOGGER.info(() -> "Person.remove: " + person);
		authService.authorize(auth);
		repository.remove(person);
	}

}
