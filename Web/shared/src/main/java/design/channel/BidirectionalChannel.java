package design.channel;

import java.util.Arrays;

public interface BidirectionalChannel<T> extends DownloadChannel<T>, UploadChannel<T> {
    T Read() throws ChannelError;

    void Write(T data) throws ChannelError;

    default String FormatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder builder = new StringBuilder();
        Arrays.asList(stackTrace)
                .forEach((el) -> builder.append(el).append("\n"));
        return builder.toString();
    }
}
