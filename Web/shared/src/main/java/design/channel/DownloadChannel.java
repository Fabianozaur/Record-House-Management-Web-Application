package design.channel;

public interface DownloadChannel<T> {
    T Read();
}
