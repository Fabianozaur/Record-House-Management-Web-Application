package design.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseReceiver<T, R, C extends Command<T, R>>{

    protected List<C> commandList;

    protected BaseReceiver(){
        this.commandList = new ArrayList<>();
    }

    public void RegisterCommand(C command){
        commandList.add(command);
    }

    public void RegisterCommands(Collection<C> commands){
        commandList.addAll(commands);
    }

    public Iterable<C> GetAllCommands(){
        return commandList;
    }
}
