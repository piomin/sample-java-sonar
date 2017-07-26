package pl.piomin.sonar.plugin;

import java.util.List;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonarsource.api.sonarlint.SonarLintSide;

@SonarLintSide
public class CustomFileCheckRegistrar implements CheckRegistrar {

	@Override
	public void register(RegistrarContext registrarContext) {
		registrarContext.registerClassesForRepository(CustomRulesDefinition.REPOSITORY_KEY, checkClasses(),
				testCheckClasses());
	}

	public static List<Class<? extends JavaCheck>> checkClasses() {
		return RulesList.getJavaChecks();
	}

	public static List<Class<? extends JavaCheck>> testCheckClasses() {
		return RulesList.getJavaTestChecks();
	}

}
