package design.channel;

import design.exception.ApplicationException;

public class ChannelError extends ApplicationException {
    public ChannelError(String message) {
        super(message);
    }

    public ChannelError(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelError(Throwable cause) {
        super(cause);
    }
}
