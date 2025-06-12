package pl.piomin.sonar.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.sonar.exception.AuthenticationException;
import pl.piomin.sonar.exception.EntityNotFoundException;
import pl.piomin.sonar.model.Gender;
import pl.piomin.sonar.model.Person;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonControllerTest {

    @Autowired
    PersonController controller;

    private static final String AUTH_HEADER = "YWRtaW46YWRtaW4=";

    @Test
    public void testFindAll() throws AuthenticationException {
        Set<Person> p = controller.findAll(AUTH_HEADER);
        assertTrue(!p.isEmpty());
    }

    @Test
    public void testFindById() throws AuthenticationException, EntityNotFoundException {
        Person p = controller.findById(1, AUTH_HEADER);
        assertNotNull(p);
    }

    @Test
    public void testFindByName() throws AuthenticationException {
        Set<Person> p = controller.findByName("Kalinowski", "Piotr", AUTH_HEADER);
        assertTrue(!p.isEmpty());
    }

    @Test
    public void testFindByLastName() throws AuthenticationException {
        Set<Person> p = controller.findByLastName("Kalinowski", AUTH_HEADER);
        assertTrue(!p.isEmpty());
    }

    @Test
    public void testAdd() throws AuthenticationException {
        Person p = controller.add(new Person(null, "X", "X", new Date(), Gender.MALE), AUTH_HEADER);
        assertNotNull(p.getId());
    }

    @Test
    public void testUpdate() throws AuthenticationException {
        controller.update(new Person(1, "X", "X", new Date(), Gender.MALE), AUTH_HEADER);
    }

    @Test
    public void testRemove() throws AuthenticationException {
        controller.remove(new Person(2, null, null, null, null), AUTH_HEADER);
    }

}