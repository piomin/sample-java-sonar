package pl.piomin.sonar.model.data;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import pl.piomin.sonar.model.Person;

/**
 * The class simulating repository for Person object
 * @author minkowp
 *
 */
public class PersonRepository {

	private Set<Person> persons;

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	public Set<Person> findAll() {
		return persons;
	}
	
	public Person findById(Integer id) {
		Optional<Person> optPerson = persons.stream().filter(it -> it.getId().equals(id)).findAny();
		if (optPerson.isPresent())
			return optPerson.get();
		else
			return null;
	}
	
	public Set<Person> findByLastName(String lastName) {
		return persons.stream().filter(it -> it.getLastName().equals(lastName)).collect(Collectors.toSet());
	}
	
	public Set<Person> findByName(String lastName, String firstName) {
		return persons.stream().filter(it -> it.getLastName().equals(lastName) && it.getFirstName().equals(firstName)).collect(Collectors.toSet());
	}
	
	public Person add(Person person) {
		if (person.getId() != null)
			throw new IllegalArgumentException("Id should not be set");
		person.setId(persons.size()+1);
		persons.add(person);
		return person;
	}
	
	public Person update(Person person) {
		if (person.getId() == null)
			throw new IllegalArgumentException("Id should be set");
		persons.remove(person);
		persons.add(person);
		return person;
	}
	
	public void remove(Person person) {
		persons.remove(person);
	}
	
}
