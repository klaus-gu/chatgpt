package xyz.openai.chatgpt.client.spring.factory;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import xyz.openai.chatgpt.client.OpenAI;
import xyz.openai.chatgpt.client.spring.annotation.GPT35Turbo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatGPT 客户端代理 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public abstract class ChatGPTServiceProxyFactory {
    
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
            
            method.getAnnotations();
            if (method.getAnnotation(GPT35Turbo.class) != null) {
                Annotation annotation = method.getAnnotation(GPT35Turbo.class);
                AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(AnnotationUtils.getAnnotationAttributes(annotation));
                System.out.println("开始处理 GPT35Turbo 模型请求");
//                result = invokeModel(method.getAnnotation(GPT35Turbo.class), method, args);
            }
            return null;
        }
        
//        private Object invokeModel(Annotation anno, Method method, Object[] args) {
//            if (anno instanceof GPT35Turbo) {
//                if (){
//
//                }
//                OpenAI.ChatGPT.ChatGPT35Turbo.config().handle(args);
//            }
//            return null;
//        }
    }
}
