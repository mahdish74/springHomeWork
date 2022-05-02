package ir.mahdi.startup.startup.infrastructure.configs;

import ir.mahdi.startup.startup.infrastructure.utils.Application;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationBeanPostProcessor implements BeanPostProcessor {
   @Autowired
   private ApplicationContext appContext;
   public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {

      return bean;
   }
   public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
      if (bean instanceof Application){
         ((Application) bean).setApplicationContext(appContext);
      }
      return bean;
   }
}