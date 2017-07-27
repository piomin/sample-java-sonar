package pl.piomin.sonar.model.data;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.piomin.sonar.model.Gender;
import pl.piomin.sonar.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	PersonRepository repository;
	
	@Test
	public void addOkTest() {
		repository.add(new Person(null, "X", "X", new Date(), Gender.MALE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addFailedTest() {
		repository.add(new Person(10, "X", "X", new Date(), Gender.MALE));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateOkTest() {
		repository.add(new Person(7, "X", "X", new Date(), Gender.MALE));
	}
	
	@Test
	public void findByIdOkTest() {
		Person p = repository.findById(4);
		Assert.assertNotNull(p);
	}
	
	@Test
	public void findByIdFailedTest() {
		Person p = repository.findById(100);
		Assert.assertNull(p);
	}
	
}
