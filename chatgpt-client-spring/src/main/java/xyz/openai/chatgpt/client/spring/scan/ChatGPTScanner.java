package xyz.openai.chatgpt.client.spring.scan;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.ClassUtils;
import xyz.openai.chatgpt.client.spring.factory.ChatGPTClientFactoryBean;
import xyz.openai.chatgpt.client.spring.factory.ChatGPTServiceProxyFactory;
import xyz.openai.chatgpt.client.spring.rule.ChatGPTUsageSpecification;

import java.util.Arrays;
import java.util.Set;

/**
 * Scanner 扫描器 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTScanner extends ClassPathBeanDefinitionScanner {
    
    private Class<?> annotationClass;
    
    private String[] basePackages;
    
    private BeanDefinitionRegistry registry;
    
    public ChatGPTScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
        this.registry = registry;
    }
    
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        final Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        this.processBeanDefinitions(beanDefinitionHolders);
        return beanDefinitionHolders;
    }
    
    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        AbstractBeanDefinition beanDefinition;
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            beanDefinition = (AbstractBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            try {
                Class actualBeanClass = ClassUtils.forName(beanClassName, this.registry.getClass().getClassLoader());
                ChatGPTUsageSpecification.methodSpecificationValidation(Arrays.asList(actualBeanClass.getMethods()));
                beanDefinition.getPropertyValues().addPropertyValue("chatGPTClient", actualBeanClass);
                ChatGPTServiceProxyFactory.getProxy(actualBeanClass);
                beanDefinition.setBeanClass(ChatGPTClientFactoryBean.class);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Class<?> getAnnotationClass() {
        return annotationClass;
    }
    
    public void setAnnotationClass(Class<?> annotationClass) {
        this.annotationClass = annotationClass;
    }
    
    public String[] getBasePackages() {
        return basePackages;
    }
    
    public void setBasePackages(String[] basePackages) {
        this.basePackages = basePackages;
    }
    
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}