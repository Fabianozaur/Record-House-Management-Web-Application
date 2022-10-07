package design.command;

public interface Command<I, R> {
    R Execute(I input);
}
