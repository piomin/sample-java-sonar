package pl.piomin.sonar;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.piomin.sonar.model.Gender;
import pl.piomin.sonar.model.Person;
import pl.piomin.sonar.model.User;
import pl.piomin.sonar.model.UserType;
import pl.piomin.sonar.model.data.PersonRepository;
import pl.piomin.sonar.model.data.UserRepository;

@SpringBootApplication
public class Application {

	private static final int PERSON_START_INDEX = 1;
	private static final int USER_START_INDEX = 1;
	
	/**
	 * Main application method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	PersonRepository repository() {
		PersonRepository repository = new PersonRepository();
		Set<Person> persons = new HashSet<>();
		int i = PERSON_START_INDEX;
		persons.add(new Person(i, "Adam", "Malinowski", new Date(), Gender.MALE));
		i++;
		persons.add(new Person(i, "Tomasz", "Janowski", new Date(), Gender.MALE));
		i++;
		persons.add(new Person(i, "Anna", "Markowska", new Date(), Gender.FEMALE));
		i++;
		persons.add(new Person(i, "Piotr", "Kalinowski", new Date(), Gender.MALE));
		i++;
		persons.add(new Person(i, "Łukasz", "Zieliński", new Date(), Gender.MALE));
		i++;
		persons.add(new Person(i, "Urszula", "Zakrzewska", new Date(), Gender.FEMALE));
		i++;
		persons.add(new Person(i, "Paweł", "Tarnowski", new Date(), Gender.MALE));
		repository.setPersons(persons);
		return repository;
	}
	
	@Bean
	UserRepository userRepository() {
		UserRepository repository = new UserRepository();
		Set<User> users = new HashSet<>();
		int i = USER_START_INDEX;
		users.add(new User(i, "manager", "manager", UserType.MANAGER));
		i++;
		users.add(new User(i, "admin", "admin", UserType.ADMIN));
		i++;
		users.add(new User(i, "reader", "reader", UserType.READER));
		i++;
		users.add(new User(i, "guest", "guest", UserType.GUEST));
		repository.setUsers(users);
		return repository;
	}
	
}
