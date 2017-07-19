package pl.piomin.sonar.model;

public class User {

	private Integer id;
	private String username;
	private String password;
	private UserType type;

	public User() {
		
	}
	
	public User(Integer id, String username, String password, UserType type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
