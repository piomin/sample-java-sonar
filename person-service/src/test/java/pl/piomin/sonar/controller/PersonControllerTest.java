package pl.piomin.sonar.controller;

import java.util.Date;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.piomin.sonar.exception.AuthenticationException;
import pl.piomin.sonar.exception.EntityNotFoundException;
import pl.piomin.sonar.model.Gender;
import pl.piomin.sonar.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
	
	@Autowired
	PersonController controller;
	
	@Test
	public void testFindAll() throws AuthenticationException {
		Set<Person> p = controller.findAll("YWRtaW46YWRtaW4=");
		Assert.assertTrue(p.size() > 0);
	}
	
	@Test
	public void testFindById() throws AuthenticationException, EntityNotFoundException {
		Person p = controller.findById(1, "YWRtaW46YWRtaW4=");
		Assert.assertNotNull(p);
	}
	
	@Test
	public void testFindByName() throws AuthenticationException {
		Set<Person> p = controller.findByName("Kalinowski", "Piotr", "YWRtaW46YWRtaW4=");
		Assert.assertTrue(p.size() > 0);
	}
	
	@Test
	public void testFindByLastName() throws AuthenticationException {
		Set<Person> p = controller.findByLastName("Kalinowski", "YWRtaW46YWRtaW4=");
		Assert.assertTrue(p.size() > 0);
	}
	
	@Test
	public void testAdd() throws AuthenticationException {
		Person p = controller.add(new Person(null, "X", "X", new Date(), Gender.MALE), "YWRtaW46YWRtaW4=");
		Assert.assertNotNull(p.getId());
	}
	
	@Test
	public void testUpdate() throws AuthenticationException {
		controller.update(new Person(1, "X", "X", new Date(), Gender.MALE), "YWRtaW46YWRtaW4=");
	}
	
	@Test
	public void testRemove() throws AuthenticationException {
		controller.remove(new Person(2, null, null, null, null), "YWRtaW46YWRtaW4=");
	}
	
}
