package recordhouse.shared.command.String;

import java.util.function.BiFunction;

public class FunctionRegexCommandMatcher extends RegexCommandMatcher {

    private String pattern;
    private BiFunction<String, String, Boolean> function;

    @Override
    public String GetPattern() {
        return pattern;
    }

    @Override
    public void UsePatternToMatch(String pattern) {
        this.pattern = pattern;
    }

    public void UseFunctionToMatch(BiFunction<String, String, Boolean> function){
        this.function = function;
    }

    public void UsePatternAndFunction(String pattern, BiFunction<String, String, Boolean> function){
        this.pattern = pattern;
        this.function = function;
    }

    @Override
    public boolean Matches(String key) {
        return function.apply(key, pattern);
    }
}
