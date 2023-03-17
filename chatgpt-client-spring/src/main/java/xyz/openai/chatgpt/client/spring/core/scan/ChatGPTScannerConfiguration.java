package xyz.openai.chatgpt.client.spring.core.scan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * ${@link ChatGPTScanner} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTScannerConfiguration implements BeanDefinitionRegistryPostProcessor {
    
    private Class annotationClass;
    
    private String[] basePackages;
    
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        ChatGPTScanner chatGPTScanner = new ChatGPTScanner(beanDefinitionRegistry);
        chatGPTScanner.setAnnotationClass(this.annotationClass);
        chatGPTScanner.setBasePackages(this.basePackages);
        chatGPTScanner.addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
        chatGPTScanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        chatGPTScanner.scan(this.basePackages);
        
    }
    
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
            throws BeansException {
        
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
}
