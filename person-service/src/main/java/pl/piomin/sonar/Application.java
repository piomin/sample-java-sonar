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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	PersonRepository repository() {
		PersonRepository repository = new PersonRepository();
		Set<Person> persons = new HashSet<>();
		persons.add(new Person(1, "Adam", "Malinowski", new Date(), Gender.MALE));
		persons.add(new Person(2, "Tomasz", "Janowski", new Date(), Gender.MALE));
		persons.add(new Person(3, "Anna", "Markowska", new Date(), Gender.FEMALE));
		persons.add(new Person(4, "Piotr", "Kalinowski", new Date(), Gender.MALE));
		persons.add(new Person(5, "Łukasz", "Zieliński", new Date(), Gender.MALE));
		persons.add(new Person(6, "Urszula", "Zakrzewska", new Date(), Gender.FEMALE));
		persons.add(new Person(7, "Paweł", "Tarnowski", new Date(), Gender.MALE));
		repository.setPersons(persons);
		return repository;
	}
	
	@Bean
	UserRepository userRepository() {
		UserRepository repository = new UserRepository();
		Set<User> users = new HashSet<>();
		users.add(new User(1, "manager", "manager", UserType.MANAGER));
		users.add(new User(2, "admin", "admin", UserType.ADMIN));
		users.add(new User(3, "reader", "reader", UserType.READER));
		users.add(new User(4, "guest", "guest", UserType.GUEST));
		repository.setUsers(users);
		return repository;
	}
	
}
