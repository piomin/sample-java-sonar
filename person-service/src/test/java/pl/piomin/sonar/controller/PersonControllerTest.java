package pl.piomin.sonar.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import pl.piomin.sonar.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	public void testFindById() {
		Person p = this.template.getForObject("/person/{id}", Person.class, 1);
		Assert.assertNotNull(p);
	}
	
	@Test
	public void testFindByName() {
		Person[] p = this.template.getForObject("/person/name/{lastName}/{firstName}", Person[].class, "Kalinowski", "Piotr");
		Assert.assertNotNull(p);
	}
	
	@Test
	public void testFindByLastName() {
		Person[] p = this.template.getForObject("/person/lastName/{lastName}", Person[].class, "Kalinowski");
		Assert.assertNotNull(p);
	}
	
}
