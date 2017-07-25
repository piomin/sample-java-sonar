package pl.piomin.sonar.plugin;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class CustomAuthorCommentCheckTest {

	@Test
	public void test() {
		JavaCheckVerifier.verify("src/test/files/CustomAuthorCommentCheck.java", new CustomAuthorCommentCheck());
	}
}
