package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext {

    private static org.springframework.context.ApplicationContext instance;

    public static org.springframework.context.ApplicationContext GetContext(){
        return instance;
    }

    public static void InitializeWith(org.springframework.context.ApplicationContext context){
        instance = context;
    }

    public static void InitializeAnnotationContext(Class<?> configuration){
        var annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(configuration);
        instance = annotationConfigApplicationContext;
        annotationConfigApplicationContext.refresh();
    }

    public static Object getInstance(String name) {
        return instance.getBean(name);
    }

    public static Object getInstance(String name, Object... args) {
        return instance.getBean(name, args);
    }

    public static <T> T getInstance(Class<T> beanClass) {
        return instance.getBean(beanClass);
    }

    public static <T> T getInstance(Class<T> beanClass, Object... args) {
        return instance.getBean(beanClass, args);
    }

    public static <T> Object getInstanceObject(String name, Class<T> beanClass) {
        return instance.getBean(name, beanClass);
    }

    public static <T> T getInstance(String name, Class<T> beanClass) {
        return instance.getBean(name, beanClass);
    }
}
