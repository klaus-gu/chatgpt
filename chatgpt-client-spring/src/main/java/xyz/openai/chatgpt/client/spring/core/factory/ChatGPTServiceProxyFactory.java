package xyz.openai.chatgpt.client.spring.core.factory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import xyz.openai.chatgpt.client.OpenAI;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapper;
import xyz.openai.chatgpt.client.spring.conversation.ConversationMapperRegistry;
import xyz.openai.chatgpt.client.spring.core.annotation.GPT35Turbo;
import xyz.openai.chatgpt.client.spring.core.registry.OpenAISettingFactoryRegistry;

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
 **/
public class ChatGPTServiceProxyFactory {
    
    private static final Map<String, Object> httpServiceCache = new ConcurrentHashMap<String, Object>();
    
    public static <T> T getProxy(Class<T> tClass) {
        return (T) httpServiceCache.computeIfAbsent(tClass.getName(), (t) -> {
            ChatGPTServiceProxy chatGPTServiceProxy = new ChatGPTServiceProxy();
            return Proxy.newProxyInstance(chatGPTServiceProxy.getClass().getClassLoader(), new Class[] {tClass},
                    chatGPTServiceProxy);
        });
    }
    
    static class ChatGPTServiceProxy implements InvocationHandler {
        
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
                if (messages.isEmpty()) {
                    throw new IllegalArgumentException(
                            "[" + method.getDeclaringClass().getCanonicalName() + "#" + method.getName()
                                    + "] no message found");
                }
                String conversationId = messages.get(0).getConversationId();
                if (annotationAttributes.get("enableContext") != null && annotationAttributes
                        .getBoolean("enableContext")) {
                    
                    ConversationMapper mapper = ConversationMapperRegistry.getConversationMapper(method);
                    
                    if (StringUtils.isEmpty(conversationId)) {
                        throw new IllegalArgumentException(
                                "[@GPT35Turbo] conversationId can not be null when enableContext is enabled");
                    }
                    mapper.appendContext(conversationId, messages);
                    messages = mapper.getContext(conversationId);
                }
                GPT35TurboRequest.Message[] messagesArr = messages.toArray(new GPT35TurboRequest.Message[] {});
                result = OpenAI.ChatGPT.ChatGPT35Turbo
                        .config(OpenAISettingFactoryRegistry.getSetting(method.getDeclaringClass()))
                        .handle(messagesArr);
                if (annotationAttributes.get("enableContext") != null && annotationAttributes
                        .getBoolean("enableContext")) {
                    ConversationMapper mapper = ConversationMapperRegistry.getConversationMapper(method);
                    if (StringUtils.isEmpty(conversationId)) {
                        throw new IllegalArgumentException(
                                "[@GPT35Turbo] conversationId can not be null when enableContext is enabled");
                    }
                    if (result != null && (result instanceof OpenAIResponse)) {
                        OpenAIResponse<GPT35TurboRequest.Message> messageOpenAIResponse = (OpenAIResponse<GPT35TurboRequest.Message>) result;
                        if (messageOpenAIResponse.res != null) {
                            List<GPT35TurboRequest.Message> msgs = new LinkedList<>();
                            msgs.add(messageOpenAIResponse.res);
                            mapper.appendContext(conversationId, msgs);
                        } else {
                            mapper.appendContext(conversationId, messageOpenAIResponse.ress);
                        }
                    }
                }
            }
            return result;
        }
    }
}
