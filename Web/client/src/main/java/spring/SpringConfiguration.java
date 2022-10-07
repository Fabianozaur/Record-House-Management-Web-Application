package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackages = {"recordhouse", "service", "ui", "shared", "rest"})
@Lazy
public class SpringConfiguration {
}
