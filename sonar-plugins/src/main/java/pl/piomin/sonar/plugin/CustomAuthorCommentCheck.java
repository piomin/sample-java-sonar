package pl.piomin.sonar.plugin;

import java.util.List;

import org.sonar.api.internal.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.SyntaxTrivia;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

@Rule(key = "CustomAuthorCommentCheck",
		name = "Javadoc comment should have @author name",
		description = "Javadoc comment should have @author name",
		priority = Priority.MAJOR,
		tags = {"style"})
public class CustomAuthorCommentCheck extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return ImmutableList.of(Kind.TRIVIA);
	}
	@Override
	public void visitTrivia(SyntaxTrivia syntaxTrivia) {
		// TODO Auto-generated method stub
		
		super.visitTrivia(syntaxTrivia);
	}
	@Override
	public void visitNode(Tree tree) {
		ClassTree c = (ClassTree) tree;
		System.out.println("CC" + c.toString());
		ModifiersTree t = c.modifiers();
		System.out.println(t.size());
	}

}
