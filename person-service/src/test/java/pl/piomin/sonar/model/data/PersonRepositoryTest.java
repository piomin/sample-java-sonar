package pl.piomin.sonar.model.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.sonar.model.Gender;
import pl.piomin.sonar.model.Person;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    public void addOkTest() {
        repository.add(new Person(null, "X", "X", new Date(), Gender.MALE));
    }

    @Test
    public void addFailedTest() {
        assertThrows(IllegalArgumentException.class, () ->
                repository.add(new Person(10, "X", "X", new Date(), Gender.MALE)));
    }

    @Test
    public void updateOkTest() {
        assertThrows(IllegalArgumentException.class, () ->
                repository.add(new Person(7, "X", "X", new Date(), Gender.MALE)));
    }

    @Test
    public void findByIdOkTest() {
        Person p = repository.findById(4);
        assertNotNull(p);
    }

    @Test
    public void findByIdFailedTest() {
        Person p = repository.findById(100);
        assertNull(p);
    }

}
