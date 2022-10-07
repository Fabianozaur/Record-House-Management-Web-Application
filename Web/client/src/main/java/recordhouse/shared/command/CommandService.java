package recordhouse.shared.command;

import org.springframework.stereotype.Service;
import recordhouse.shared.command.String.StringCommandReceiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class CommandService {

    private final StringCommandReceiver receiver;
    private final ExecutorService executorService;

    public CommandService(StringCommandReceiver receiver, ExecutorService executorService) {
        this.receiver = receiver;
        this.executorService = executorService;
    }

    public String GetMenuString(){
        return receiver.FormatAsMenu();
    }

    public Future<String> Handle(String input){
        var command = receiver.MatchCommand(input);
        return executorService.submit(() -> command
                .map(c -> c.Execute(input))
                .orElse(receiver.GetNotFoundText(input)));
    }
}
