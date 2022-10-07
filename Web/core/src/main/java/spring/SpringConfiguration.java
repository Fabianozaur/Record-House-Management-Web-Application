package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"recordhouse", "service", "shared"})
@Lazy
@EnableTransactionManagement
public class SpringConfiguration {
}
