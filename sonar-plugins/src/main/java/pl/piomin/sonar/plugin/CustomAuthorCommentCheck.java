package pl.piomin.sonar.plugin;

import java.util.List;

import org.sonar.api.internal.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.SyntaxTrivia;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(key = "CustomAuthorCommentCheck",
		name = "Javadoc comment should have @author name",
		description = "Javadoc comment should have @author name",
		priority = Priority.MAJOR,
		tags = {"style"})
public class CustomAuthorCommentCheck extends IssuableSubscriptionVisitor {

	private static final String MSG_NO_COMMENT = "There is no comment under class";
	private static final String MSG_NO_AUTHOR = "There is no author inside comment";
	
	private Tree actualTree = null;
	
	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Kind.TRIVIA, Kind.CLASS);
	}
	
	@Override
	public void visitTrivia(SyntaxTrivia syntaxTrivia) {
		String comment = syntaxTrivia.comment();
		if (syntaxTrivia.column() != 0)
			return;
		if (comment == null) {
			reportIssue(actualTree, MSG_NO_COMMENT);
			return;
		}
		if (!comment.contains("@author")) {
			reportIssue(actualTree, MSG_NO_AUTHOR);
			return;
		}
	}

	@Override
	public void visitNode(Tree tree) {
		if (tree.is(Kind.CLASS)) {
			actualTree = tree;
		}
	}

}
