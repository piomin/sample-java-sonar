package pl.piomin.sonar.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import pl.piomin.sonar.exception.AuthenticationException;
import pl.piomin.sonar.model.User;
import pl.piomin.sonar.model.UserType;
import pl.piomin.sonar.model.data.UserRepository;

@Service
/**
 * The class performing authentication and authorization
 * @author minkowp
 *
 */
public class AuthorizationService {

	private static final Logger LOGGER = Logger.getLogger(AuthorizationService.class.getName());
	
	@Autowired
	UserRepository repository;
	
	/**
	 * Main authentication method
	 * @param authHeader
	 * @return
	 * @throws AuthenticationException
	 */
	public boolean authorize(String authHeader) throws AuthenticationException {
		final String tmpHeader = authHeader.replaceAll("Basic ", "");
		final String header = new String(Base64Utils.decodeFromString(tmpHeader));
		LOGGER.info(() -> "authorize: " + header);
		String[] tokens = header.split(":");
		User user = repository.findByUsername(tokens[0]);
		if (user == null || !user.getPassword().equals(tokens[1]))
			throw new AuthenticationException("User not authenticated");
		if (user.getType() == UserType.GUEST) {
			throw new AuthenticationException("User not allowed to call this method"); 
		} else {
			LOGGER.info("User is allowed to call this method");
		}
		return true;
	}
	
}
