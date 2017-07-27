package pl.piomin.sonar.model.data;

import java.util.Optional;
import java.util.Set;

import pl.piomin.sonar.model.User;

/**
 * The class simulating repository for User object
 * @author minkowp
 *
 */
public class UserRepository {

	private Set<User> users;

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> findAll() {
		return users;
	}

	public User findById(Integer id) {
		Optional<User> optUser =  users.stream().filter(it -> it.getId().equals(id)).findAny();
		if (optUser.isPresent())
			return optUser.get();
		else
			return null;
	}

	public User findByUsername(String username) {
		Optional<User> optUser =  users.stream().filter(it -> it.getUsername().equals(username)).findAny();
		if (optUser.isPresent())
			return optUser.get();
		else
			return null;
	}

}
