package shared.channel.console;

import design.channel.BidirectionalChannel;
import design.channel.ChannelError;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

@Service
@Scope("singleton")
public class ConsoleChannel implements BidirectionalChannel<String> {
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedWriter error;

    private static int BufferSize = 8192;

    public ConsoleChannel() {
        in = new BufferedReader(new InputStreamReader(new FileInputStream(FileDescriptor.in)));
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out)));
        error = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.err)));
    }

    @Override
    public String Read() throws ChannelError {
        try {
            char[] buffer = new char[BufferSize];
            if (in.read(buffer) == -1)
                return "";
            return new String(buffer);
        } catch (IOException e) {
            throw new ConsoleStreamError(e);
        }
    }

    @Override
    public void Write(String data) throws ChannelError {
        try {
            out.write(data);
            out.flush();
        } catch (IOException e) {
            throw new ConsoleStreamError(e);
        }
    }

    public String readLine() {
        try {
            return NormalizeLineSeparator(in.readLine());
        } catch (IOException e) {
            throw new ConsoleStreamError(e);
        }
    }

    private String NormalizeLineSeparator(String text) {
        String result = Optional.ofNullable(text).orElse("");
        return !result.isEmpty() ? result.replace("\n", System.lineSeparator()).replace("\r\n", System.lineSeparator()) : "";
    }

    public void writeLine(String text) {
        try {
            out.write(text + "\n");
            out.flush();
        } catch (IOException e) {
            throw new ConsoleStreamError(e);
        }
    }

    public void writeError(String errorText) {
        try {
            error.write(errorText);
        } catch (IOException e) {
            throw new ConsoleStreamError(e);
        }
    }
}
