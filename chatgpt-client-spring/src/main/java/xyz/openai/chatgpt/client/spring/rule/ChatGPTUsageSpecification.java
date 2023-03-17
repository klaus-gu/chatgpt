package xyz.openai.chatgpt.client.spring.rule;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.annotation.GPT35Turbo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * 校验 gpt 部分ai模型使用的方法参数 .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public interface ChatGPTUsageSpecification {
    
    
    
    public static void methodSpecificationValidation(List<Method> methods) {
        methods.forEach(ChatGPTUsageSpecification::methodSpecificationValidation);
    }
    
    public static void methodSpecificationValidation(Method method) {
        if (method.getAnnotation(GPT35Turbo.class) != null) {
            gpt35TurboMethodSpecificationValidation(method);
        }
    }
    
    public static void gpt35TurboMethodSpecificationValidation(Method method) {
        final Parameter[] parameters = method.getParameters();
        if (!method.getReturnType().isAssignableFrom(OpenAIResponse.class)) {
            throw new IllegalArgumentException("[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                    + "] The expected return type is OpenAIResponse when use gpt35turbo model. ");
        }
        Parameter parameter = parameters[0];
        if (parameters.length > 1 || !parameter.getType().isAssignableFrom(GPT35TurboRequest.Message.class)) {
            throw new IllegalArgumentException("[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                    + "] The expected return type is OpenAIResponse when use gpt35turbo model. ");
        }
    }
    
    
}
