package ir.mahdi.startup.startup.infrastructure.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public interface Application {
    ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext();;
}
