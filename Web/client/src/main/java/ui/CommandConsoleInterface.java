package ui;

import design.exception.FatalError;
import org.springframework.stereotype.Service;
import recordhouse.shared.command.CommandService;
import shared.channel.FatalChannelError;
import shared.channel.console.ConsoleChannel;

import java.util.concurrent.ExecutionException;

/**
 * @author horatzio.
 */

@Service
public class CommandConsoleInterface {

    private CommandService service;
    private ConsoleChannel stream;

    public static boolean BreakOnFatalError = false;
    public static boolean BreakOnRuntimeError = false;

    public CommandConsoleInterface(CommandService service, ConsoleChannel stream) {
        this.service = service;
        this.stream = stream;
    }

    public void run() {
        boolean Running = true;
        while (Running) {
            try {
                try {
                    WriteOutput(service.GetMenuString());
                    String input = ReadInput();
                    var output = service.Handle(input);

                    WriteOutput(output.get());
                }
                catch(FatalError e){
                    // application goes into unrecoverable state
                    HandleError(e);
                    if (BreakOnFatalError) {
                        e.printStackTrace();
                        break;
                    }
                }catch(RuntimeException e){
                    HandleError(e);
                    if (BreakOnRuntimeError) {
                        e.printStackTrace();
                        break;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    HandleError(e);
                }
            } catch(FatalChannelError e) {
                e.printStackTrace();
                break;
                // application crash -> log to file
            }
        }
    }

    private String ReadInput(){
        return stream.readLine();
    }

    private void WriteOutput(String output){
        stream.writeLine(output);
    }

    private void HandleError(Exception e){
        String stackTrace = stream.FormatStackTrace(e.getStackTrace());
        stream.writeLine(String.format("OOPS! Something went wrong:\n%s", e.getMessage()));
        stream.writeError(stackTrace);
    }
}
