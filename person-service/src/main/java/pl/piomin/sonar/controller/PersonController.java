package pl.piomin.sonar.controller;

import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
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
import pl.piomin.sonar.model.User;
import pl.piomin.sonar.model.data.PersonRepository;
import pl.piomin.sonar.model.data.UserRepository;
import pl.piomin.sonar.service.AuthorizationService;

@RestController
public class PersonController {

	Logger logger = Logger.getLogger(PersonController.class.getName());
	
	@Autowired
	AuthorizationService authService;
	@Autowired
	PersonRepository repository;
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public Set<Person> findAll(@RequestHeader("Authorization") String auth) throws AuthenticationException {
		logger.info("Person.findAll");
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		return repository.findAll();
	}
	
	@GetMapping("/person/lastName/{lastName}")
	public Set<Person> findByLastName(@PathVariable("lastName") String lastName, @RequestHeader("Authorization") String auth) throws AuthenticationException {
		logger.info("Person.findByLastName: " + lastName);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		return repository.findByLastName(lastName);
	}
	
	@GetMapping("/person/name/{lastName}/{firstName}")
	public Set<Person> findByName(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName, @RequestHeader("Authorization") String auth) throws AuthenticationException {
		logger.info("Person.findByName: " + lastName + ", " + firstName);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		return repository.findByName(lastName, firstName);
	}
	
	@GetMapping("/person/{id}")
	public Person findById(@PathVariable("id") Integer id, @RequestHeader("Authorization") String auth) throws AuthenticationException, EntityNotFoundException {
		logger.info("Person.findById: " + id);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		Person p = repository.findById(id);
		if (p != null) {
			final int years = (int) (System.currentTimeMillis() - p.getBirthDate().getTime()) / (1000 * 60 * 60 * 24 * 365);
			if (years < 11)
				p.setCategory(PersonCategory.KID);
			else if (years < 18)
				p.setCategory(PersonCategory.TEENAGER);
			else if (years < 60)
				p.setCategory(PersonCategory.GROWN);
			else
				p.setCategory(PersonCategory.PENSIONARY);
			return p;
		} else {
			throw new EntityNotFoundException("Person " + p.getId() + "nof found");
		}
	}
	
	@PostMapping("/person")
	public Person add(Person person, @RequestHeader("Authorization") String auth) throws InvalidEntityException, AuthenticationException {
		logger.info("Person.add: " + person);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		return repository.add(person);
	}
	
	@PutMapping("/person")
	public Person update(Person person, @RequestHeader("Authorization") String auth) throws InvalidEntityException, AuthenticationException {
		logger.info("Person.update: " + person);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		return repository.update(person);
	}
	
	@DeleteMapping("/person")
	public void remove(Person person, @RequestHeader("Authorization") String auth) throws AuthenticationException {
		logger.info("Person.remove: " + person);
		String header = auth.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = userRepository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		switch (user.getType()) {
		case GUEST:
			throw new AuthenticationException("User not allowed to call this method"); 
		default:
			logger.info("User is allowed to call this method");
		}
		repository.remove(person);
	}
	
}
