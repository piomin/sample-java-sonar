package pl.piomin.sonar.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import pl.piomin.sonar.exception.AuthenticationException;
import pl.piomin.sonar.model.User;
import pl.piomin.sonar.model.data.UserRepository;

@Service
public class AuthorizationService {

	Logger logger = Logger.getLogger(AuthorizationService.class.getName());
	
	@Autowired
	UserRepository repository;
	
	public boolean authorize(String authHeader) throws AuthenticationException {
		String header = authHeader.replaceAll("Basic ", "");
		header = new String(Base64Utils.decodeFromString(header));
		logger.info("authorize: " + header);
		String[] tokens = header.split(":");
		User user = repository.findByUsername(tokens[0]);
		if (user == null || user.getPassword() != tokens[1])
			throw new AuthenticationException("User not authenticated");
		return true;
	}
	
}
