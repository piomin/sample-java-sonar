package pl.piomin.sonar.plugin;

import java.util.ArrayList;
import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

public final class RulesList {

    private RulesList() {

    }

    @SuppressWarnings("rawtypes")
    public static List<Class> getChecks() {
        List<Class> l = new ArrayList<>();
        l.addAll(getJavaChecks());
        l.addAll(getJavaTestChecks());
        return l;
    }

    public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
        return List.of();
    }

    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        return List.of(CustomAuthorCommentCheck.class);
    }
}
