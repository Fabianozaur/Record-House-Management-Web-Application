package design.command;

public interface CommandMatcher<T> {
    boolean Matches(T key);
}
