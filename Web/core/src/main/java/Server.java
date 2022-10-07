import spring.ApplicationContext;
import spring.SpringConfiguration;

public class Server {
    public static void main(String[] args){
        ApplicationContext.InitializeAnnotationContext(SpringConfiguration.class);
    }
}
