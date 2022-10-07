package shared.channel;

import design.channel.ChannelError;

public class FatalChannelError extends ChannelError {
    public FatalChannelError(String message) {
        super(message);
    }

    public FatalChannelError(String message, Throwable cause) {
        super(message, cause);
    }

    public FatalChannelError(Throwable cause) {
        super(cause);
    }
}
