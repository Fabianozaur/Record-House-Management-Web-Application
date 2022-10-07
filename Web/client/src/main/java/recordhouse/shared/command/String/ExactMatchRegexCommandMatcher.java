package recordhouse.shared.command.String;

import java.util.regex.Pattern;

public class ExactMatchRegexCommandMatcher extends RegexCommandMatcher{

    private String pattern;

    public ExactMatchRegexCommandMatcher(String pattern){
        this.pattern = pattern;
    }

    @Override
    public String GetPattern() {
        return pattern;
    }

    @Override
    public void UsePatternToMatch(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean Matches(String key) {
        return Pattern.matches(pattern, key);
    }
}
