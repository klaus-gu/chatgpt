package xyz.openai.chatgpt.client.spring.core.registry;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.spring.core.factory.OpenAISettingFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for ${@link xyz.openai.chatgpt.client.setting.OpenAISetting} .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAISettingFactoryRegistry implements BeanFactoryAware, SmartInitializingSingleton {
    
    private static final Map<Class, Class<? extends OpenAISettingFactory>> settingFactorys = new ConcurrentHashMap<>();
    
    private static final Map<Class, Class<? extends OpenAISettingFactory>> settingFactoryBeans = new ConcurrentHashMap<>();
    
    private static final Map<Class, OpenAISettingFactory> factorys = new ConcurrentHashMap<>();
    
    private BeanFactory beanFactory;
    
    public static void registerSettingClazz(Class clazz0, Class<? extends OpenAISettingFactory> clazz1) {
        settingFactorys.put(clazz0, clazz1);
    }
    
    public static void registerSettingBeanClazz(Class clazz0, Class<? extends OpenAISettingFactory> clazz1) {
        settingFactoryBeans.put(clazz0, clazz1);
    }
    
    public static OpenAISetting getSetting(Class clazz) {
        return factorys.get(clazz).getSetting();
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    @Override
    public void afterSingletonsInstantiated() {
        for (Map.Entry<Class, Class<? extends OpenAISettingFactory>> entry : settingFactorys.entrySet()) {
            factorys.put(entry.getKey(), BeanUtils.instantiateClass(entry.getValue()));
        }
        for (Map.Entry<Class, Class<? extends OpenAISettingFactory>> entry : settingFactoryBeans.entrySet()) {
            factorys.put(entry.getKey(), this.beanFactory.getBean(entry.getValue()));
        }
    }
}
