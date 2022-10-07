package design.channel;

public interface UploadChannel<T> {
    void Write(T object);
}
