package pl.piomin.sonar.plugin;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class CustomAuthorCommentCheckTest {

	@Test
	public void testOk() {
		JavaCheckVerifier.verifyNoIssue("src/test/files/CustomAuthorCommentCheck.java", new CustomAuthorCommentCheck());
	}
	
	@Test
	public void testFail() {
		JavaCheckVerifier.verify("src/test/files/CustomAuthorCommentCheckFail.java", new CustomAuthorCommentCheck());
	}
	
}
