package pl.piomin.sonar.model.data;

import java.util.Optional;
import java.util.Set;

import pl.piomin.sonar.model.User;

/**
 * The class simulating repository for User object
 *
 * @author minkowp
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
        return users.stream()
                .filter(it -> it.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(it -> it.getUsername().equals(username))
                .findAny()
                .orElse(null);
    }

}
