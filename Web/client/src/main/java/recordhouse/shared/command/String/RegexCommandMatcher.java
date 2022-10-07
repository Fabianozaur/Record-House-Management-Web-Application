package recordhouse.shared.command.String;

import design.command.CommandMatcher;

public abstract class RegexCommandMatcher implements CommandMatcher<String> {

    public abstract String GetPattern();
    public abstract void UsePatternToMatch(String pattern);
}
