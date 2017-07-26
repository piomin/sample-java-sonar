package pl.piomin.sonar.plugin;

import java.util.List;

import com.google.common.collect.ImmutableList;
import org.sonar.plugins.java.api.JavaCheck;

public final class RulesList {

	private RulesList() {
		
	}

	@SuppressWarnings("rawtypes")
	public static List<Class> getChecks() {
		return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
	}

	public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
		return ImmutableList.<Class<? extends JavaCheck>>builder().build();
	}

	public static List<Class<? extends JavaCheck>> getJavaChecks() {
		return ImmutableList.<Class<? extends JavaCheck>>builder().add(CustomAuthorCommentCheck.class).build();
	}
}
