package recordhouse.shared.command.String;

import design.channel.BidirectionalChannel;
import design.command.Command;

public abstract class StringCommand implements Command<String, String> {

    private String key;
    private String name;
    private String text;
    private BidirectionalChannel<String> channel;
    private RegexCommandMatcher matcher;
    private boolean RequiresChannel;

    public StringCommand(String key, String text){
        this(key, text, false);
    }

    public StringCommand(String key, String text, boolean requiresChannel){
        this.key = key;
        this.name = key;
        this.text = text;
        this.RequiresChannel = requiresChannel;
        this.matcher = new ExactMatchRegexCommandMatcher(key);
    }

    public BidirectionalChannel<String> GetCommandChannel(){
        return channel;
    }

    public boolean RequiresChannel(){
        return RequiresChannel;
    }

    public void SetCommandChannel(BidirectionalChannel<String> channel){
        this.channel = channel;
    }

    public void SetCommandMatcher(RegexCommandMatcher matcher){ this.matcher = matcher; }

    public RegexCommandMatcher GetCommandMatcher() {
        return matcher;
    }
    public String GetCommandKey(){ return this.key; }
    public String GetCommandName() { return this.name; }
    public String GetCommandText() { return this.text;}

    public abstract String Execute(String input);
}
