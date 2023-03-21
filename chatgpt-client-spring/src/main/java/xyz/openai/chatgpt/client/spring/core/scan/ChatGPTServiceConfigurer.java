package xyz.openai.chatgpt.client.spring.core.scan;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperRegistry;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTScan;
import xyz.openai.chatgpt.client.spring.core.registry.OpenAISettingFactoryRegistry;

/**
 * ${@link xyz.openai.chatgpt.client.spring.core.scan.ChatGPTScannerConfiguration} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTServiceConfigurer implements ImportBeanDefinitionRegistrar {
    
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        final AnnotationAttributes annotationAttributes = AnnotationAttributes
                .fromMap(annotationMetadata.getAnnotationAttributes(ChatGPTScan.class.getName()));
        if (annotationAttributes != null) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ChatGPTScannerConfiguration.class);
            BeanDefinition beanDefinition = builder.getBeanDefinition();
            if (annotationAttributes.get("basePackages") != null) {
                beanDefinition.getPropertyValues().add("basePackages", annotationAttributes.get("basePackages"));
            }
            if (annotationAttributes.get("annotationClass") != null) {
                beanDefinition.getPropertyValues().add("annotationClass", annotationAttributes.get("annotationClass"));
            }
            registry.registerBeanDefinition(ChatGPTScannerConfiguration.class.getName(), beanDefinition);
        }
        BeanDefinition conversationMapperRegistryBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(ConversationMapperRegistry.class).getBeanDefinition();
        registry.registerBeanDefinition(ConversationMapperRegistry.class.getName(),
                conversationMapperRegistryBeanDefinition);
    
        BeanDefinition openAISettingFactoryRegistryBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(OpenAISettingFactoryRegistry.class).getBeanDefinition();
        registry.registerBeanDefinition(OpenAISettingFactoryRegistry.class.getName(),
                openAISettingFactoryRegistryBeanDefinition);
    }
}
