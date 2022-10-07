package shared.channel.console;

import shared.channel.FatalChannelError;

public class ConsoleStreamError extends FatalChannelError {
    public ConsoleStreamError(String message) {
        super(message);
    }

    public ConsoleStreamError(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleStreamError(Throwable cause) {
        super(cause);
    }
}
