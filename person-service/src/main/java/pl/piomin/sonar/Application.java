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
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    PersonRepository repository() {
        PersonRepository repository = new PersonRepository();
        Set<Person> persons = new HashSet<>();
        persons.add(new Person(PERSON_START_INDEX, "Adam", "Malinowski", new Date(), Gender.MALE));
        persons.add(new Person(PERSON_START_INDEX + 1, "Tomasz", "Janowski", new Date(), Gender.MALE));
        persons.add(new Person(PERSON_START_INDEX + 2, "Anna", "Markowska", new Date(), Gender.FEMALE));
        persons.add(new Person(PERSON_START_INDEX + 3, "Piotr", "Kalinowski", new Date(), Gender.MALE));
        persons.add(new Person(PERSON_START_INDEX + 4, "Łukasz", "Zieliński", new Date(), Gender.MALE));
        persons.add(new Person(PERSON_START_INDEX + 5, "Urszula", "Zakrzewska", new Date(), Gender.FEMALE));
        persons.add(new Person(PERSON_START_INDEX + 6, "Paweł", "Tarnowski", new Date(), Gender.MALE));
        repository.setPersons(persons);
        return repository;
    }

    @Bean
    UserRepository userRepository() {
        UserRepository repository = new UserRepository();
        Set<User> users = new HashSet<>();
        users.add(new User(USER_START_INDEX, "manager", "manager", UserType.MANAGER));
        users.add(new User(USER_START_INDEX + 1, "admin", "admin", UserType.ADMIN));
        users.add(new User(USER_START_INDEX + 2, "reader", "reader", UserType.READER));
        users.add(new User(USER_START_INDEX + 3, "guest", "guest", UserType.GUEST));
        repository.setUsers(users);
        return repository;
    }

}