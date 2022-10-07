package shared.channel;

import design.channel.BidirectionalChannel;
import design.channel.ChannelError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public abstract class BaseFileChannel<T> implements BidirectionalChannel<T> {

    protected static String storagePath;

    static {
        try {
            storagePath = new File("./").getCanonicalPath() + "/storage";
        } catch (IOException e) {
            throw new ChannelError(e);
        }
    }

    protected String filePath;
    protected String fileName;

    protected BaseFileChannel(String fileName) {
        this.fileName = fileName;
        this.filePath = String.format("%s/%s", storagePath, this.fileName);
    }

    public abstract T Read();

    public abstract void Write(T object);

    public String GetFileName() {
        return fileName;
    }

    protected void CreateIfNotExists() throws IOException {

        Optional.of(IsDirectoryPresent())
                .filter(b -> b)
                .ifPresentOrElse((b) -> {
                }, () -> {
                    Path directory = Paths.get(storagePath);
                    try {
                        Files.createDirectory(directory);
                    } catch (IOException e) {
                        throw new ChannelError(e);
                    }
                });

        Optional.of(IsFilePresent())
                .filter(b -> b)
                .ifPresentOrElse((b) -> {
                }, () -> {
                    Path file = Paths.get(filePath);
                    try {
                        Files.createFile(file);
                    } catch (IOException e) {
                        throw new ChannelError(e);
                    }
                });
    }

    public boolean IsDirectoryPresent() {
        Path path = Paths.get(storagePath);
        return Files.exists(path) && Files.isDirectory(path);
    }

    public boolean IsFilePresent() {
        if (!IsDirectoryPresent())
            return false;
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    protected void ClearFile() throws IOException {
        Path path = Paths.get(filePath);
        FileChannel.open(path, StandardOpenOption.WRITE).truncate(0).close();
    }

    public boolean CanRead() {
        try {
            return TryCanRead();
        } catch (IOException e) {
            throw new ChannelError(e);
        }
    }

    private boolean TryCanRead() throws IOException {
        File file = Paths.get(filePath).toFile();
        if (!file.exists() || !file.canRead())
            return false;
        try (FileInputStream ignored = new FileInputStream(filePath)) {
            return true;
        }
    }

    public boolean CanWrite() {
        try {
            return TryCanWrite();
        } catch (IOException e) {
            throw new ChannelError(e);
        }
    }

    private boolean TryCanWrite() throws IOException {
        File file = Paths.get(filePath).toFile();
        if (!file.exists() || !file.canWrite())
            return false;
        try (FileOutputStream ignored = new FileOutputStream(filePath)) {
            return true;
        }
    }

    public boolean IsEmpty() {
        try {
            return TryIsEmpty();
        } catch (IOException e) {
            throw new ChannelError(e);
        }
    }

    private boolean TryIsEmpty() throws IOException {
        if (!IsFilePresent())
            return false;

        try (FileInputStream in = new FileInputStream(filePath)) {
            return in.available() <= 0;
        }
    }
}
