package xyz.openai.chatgpt.client.spring.factory;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import xyz.openai.chatgpt.client.OpenAI;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.setting.OpenAISetting;
import xyz.openai.chatgpt.client.spring.annotation.GPT35Turbo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatGPT http client proxy factory .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTServiceProxyFactory {
    
    private static final Map<String, Object> httpServiceCache = new ConcurrentHashMap<String, Object>();
    
    public static <T> T getProxy(Class<T> tClass, OpenAISettingFactory openAISettingFactory) {
        return (T) httpServiceCache.computeIfAbsent(tClass.getName(), (t) -> {
            ChatGPTServiceProxy chatGPTServiceProxy = new ChatGPTServiceProxy(openAISettingFactory);
            return Proxy.newProxyInstance(chatGPTServiceProxy.getClass().getClassLoader(), new Class[] {tClass},
                    chatGPTServiceProxy);
        });
    }
    
    static class ChatGPTServiceProxy implements InvocationHandler {
        
        private final OpenAISettingFactory openAISettingFactory;
        
        public ChatGPTServiceProxy(OpenAISettingFactory openAISettingFactory) {
            this.openAISettingFactory = openAISettingFactory;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            method.getAnnotations();
            if (method.getAnnotation(GPT35Turbo.class) != null) {
                Annotation annotation = method.getAnnotation(GPT35Turbo.class);
                AnnotationAttributes annotationAttributes = AnnotationAttributes
                        .fromMap(AnnotationUtils.getAnnotationAttributes(annotation));
                Object firstArg = args[0];
                List<GPT35TurboRequest.Message> messages = new LinkedList<>();
                if (firstArg instanceof GPT35TurboRequest.Message) {
                    messages.add((GPT35TurboRequest.Message) firstArg);
                } else if (firstArg instanceof List) {
                    messages = (List<GPT35TurboRequest.Message>) firstArg;
                }
                GPT35TurboRequest.Message[] messagesArr = messages.toArray(new GPT35TurboRequest.Message[] {});
                result = OpenAI.ChatGPT.ChatGPT35Turbo.config(openAISettingFactory.getSetting()).handle(messagesArr);
            }
            return result;
        }
    }
}
