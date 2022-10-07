package recordhouse.shared.command.String;

import design.command.BaseReceiver;

import java.util.Optional;

public class StringCommandReceiver extends BaseReceiver<String, String, StringCommand> {

    public Optional<StringCommand> MatchCommand(String input){
        var result = commandList.stream()
                .filter(c -> c.GetCommandMatcher().Matches(input))
                .findFirst();
        if(result.isEmpty())
            return commandList.stream()
                    .filter(c -> c.GetCommandName().equals(input))
                    .findFirst();
        return result;
    }

    public String FormatAsMenu(){
        StringBuilder stringBuilder = new StringBuilder();
        commandList.forEach(
                c -> stringBuilder
                        .append(String.format("%s - %s\n", c.GetCommandKey(), c.GetCommandText()))
        );
        return stringBuilder.toString();
    }

    public String GetNotFoundText(String input){
        return String.format("Unable to find command matching: %s", input);
    }
}
