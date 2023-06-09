package xyz.openai.chatgpt.client.spring.core.scan;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperFactory;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperRegistry;
import xyz.openai.chatgpt.client.spring.core.annotation.ChatGPTClient;
import xyz.openai.chatgpt.client.spring.core.annotation.GPT35Turbo;
import xyz.openai.chatgpt.client.spring.core.factory.ChatGPTClientFactoryBean;
import xyz.openai.chatgpt.client.spring.core.factory.ChatGPTServiceProxyFactory;
import xyz.openai.chatgpt.client.spring.core.factory.OpenAISettingFactory;
import xyz.openai.chatgpt.client.spring.core.registry.OpenAISettingFactoryRegistry;
import xyz.openai.chatgpt.client.spring.core.rule.ChatGPTUsageSpecification;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * GPT Client Scanner .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
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
                processChatGPTSetting(actualBeanClass, beanDefinition);
                processConversationContext(actualBeanClass);
                beanDefinition.getPropertyValues().addPropertyValue("chatGPTClient", actualBeanClass);
                ChatGPTServiceProxyFactory.getProxy(actualBeanClass);
                beanDefinition.setBeanClass(ChatGPTClientFactoryBean.class);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void processConversationContext(Class actualClazz) {
        Method[] methods = actualClazz.getMethods();
        //        Map<Method, ConversationMapper> conversationMapperCache = new HashMap<>(methods.length);
        if (methods.length > 0) {
            List<Method> methodList = Arrays.asList(methods);
            for (Method method : methodList) {
                if (method.isAnnotationPresent(GPT35Turbo.class)) {
                    Annotation annotation = method.getAnnotation(GPT35Turbo.class);
                    AnnotationAttributes annotationAttributes = AnnotationAttributes
                            .fromMap(AnnotationUtils.getAnnotationAttributes(annotation));
                    // enableContext = true
                    if (annotationAttributes.get("enableContext") != null && annotationAttributes
                            .getBoolean("enableContext")) {
                        // check if conversationMapper is right set
                        Object conversationMapperFactory = annotationAttributes.get("conversationMapperFactory");
                        if (conversationMapperFactory.getClass().isInterface() || Modifier
                                .isAbstract(conversationMapperFactory.getClass().getModifiers())) {
                            throw new IllegalArgumentException(
                                    "[@GPT35Turbo#conversationMapperFactory()] Expected to be a concrete class，but find interface or abstract class");
                        }
                        Class clazz = (Class<? extends ConversationMapperFactory>) conversationMapperFactory;
                        final String[] namesForType = ((ConfigurableListableBeanFactory) this.registry)
                                .getBeanNamesForType(clazz);
                        if (namesForType.length == 0) {
                            ConversationMapperRegistry.registerMapperClazz(method, clazz);
                        } else {
                            ConversationMapperRegistry.registerMapperBeanClazz(method, clazz);
                        }
                    }
                }
            }
        }
    }
    
    private void processChatGPTSetting(Class actualClazz, AbstractBeanDefinition beanDefinition) {
        Annotation annotation = actualClazz.getAnnotation(ChatGPTClient.class);
        if (annotation != null) {
            AnnotationAttributes annotationAttributes = AnnotationAttributes
                    .fromMap(AnnotationUtils.getAnnotationAttributes(annotation));
            Object settingFactory = annotationAttributes.get("settingFactory");
            if (settingFactory != null) {
                Class clazz = (Class<? extends OpenAISettingFactory>) settingFactory;
                final String[] names = ((ConfigurableListableBeanFactory) this.registry).getBeanNamesForType(clazz);
                
                if (names.length == 0) {
                    OpenAISettingFactoryRegistry.registerSettingClazz(actualClazz, clazz);
                } else {
                    OpenAISettingFactoryRegistry.registerSettingBeanClazz(actualClazz, clazz);
                }
                
                //                final OpenAISettingFactory openAISettingFactory = BeanUtils
                //                        .instantiateClass(clazz, OpenAISettingFactory.class);
                //                beanDefinition.getPropertyValues().addPropertyValue("openAISettingFactory", openAISettingFactory);
                return;
            }
        }
        throw new IllegalArgumentException(
                "[ChatGPTScanner#processBeanDefinitions] Expect with @ChatGPTClient annotation on current class ,but found none.");
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
