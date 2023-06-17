package pl.piomin.sonar.plugin;

import org.junit.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class CustomAuthorCommentCheckTest {

	@Test
	public void testOk() {
//		CheckVerifier.newVerifier().verifyIssueOnFile("src/test/files/CustomAuthorCommentCheck.java", new CustomAuthorCommentCheck());
	}
	
	@Test
	public void testFail() {
//		CheckVerifier.newVerifier().verify("src/test/files/CustomAuthorCommentCheckFail.java", new CustomAuthorCommentCheck());
	}
	
}
