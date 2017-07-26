package pl.piomin.sonar.plugin;

import org.sonar.api.Plugin;

public class CustomRulesPlugin implements Plugin {

	  @Override
	  public void define(Context context) {
	    context.addExtension(CustomRulesDefinition.class);
	    context.addExtension(CustomFileCheckRegistrar.class);
	  }

	  
}
