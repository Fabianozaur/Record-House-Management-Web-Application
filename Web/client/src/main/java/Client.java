import spring.ApplicationContext;
import spring.SpringConfiguration;
import ui.CommandConsoleInterface;

public class Client {
    public static void main(String[] args) {
        ApplicationContext.InitializeAnnotationContext(SpringConfiguration.class);
        var console = ApplicationContext.getInstance(CommandConsoleInterface.class);
        console.run();
    }
}
