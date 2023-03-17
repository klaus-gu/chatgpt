package xyz.openai.chatgpt.client.spring.core.rule;

import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;
import xyz.openai.chatgpt.client.spring.core.annotation.GPT35Turbo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Used to validate method witch is annotated by gpt annotation .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public interface ChatGPTUsageSpecification {
    
    
    static void methodSpecificationValidation(List<Method> methods) {
        methods.forEach(ChatGPTUsageSpecification::methodSpecificationValidation);
    }
    
    static void methodSpecificationValidation(Method method) {
        if (method.getAnnotation(GPT35Turbo.class) != null) {
            gpt35TurboMethodSpecificationValidation(method);
        }
    }
    
    static void gpt35TurboMethodSpecificationValidation(Method method) {
        
        if (!method.getReturnType().isAssignableFrom(OpenAIResponse.class)) {
            throw new IllegalArgumentException("[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                    + "] The expected return type is OpenAIResponse when use gpt35turbo model. ");
        }
        final Parameter[] parameters = method.getParameters();
        if (parameters.length != 1) {
            throw new IllegalArgumentException("[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                    + "] The expected request parameter number is one but fount " + parameters.length + ".");
        }
        Parameter parameter = parameters[0];
        
        if (!(parameter.getType().isAssignableFrom(GPT35TurboRequest.Message.class))) {
            Type[] actualTypeArguments = method.getGenericParameterTypes();
            if (actualTypeArguments.length != 1) {
                throw new IllegalArgumentException(
                        "[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                                + "] The expected request parameter type is GPT35TurboRequest.Message or List<GPT35TurboRequest.Message> when use gpt35turbo model. ");
            }
            ParameterizedType parameterizedType = (ParameterizedType)actualTypeArguments[0];
            final Type[] actTypes = parameterizedType.getActualTypeArguments();
            if (actTypes.length != 1) {
                throw new IllegalArgumentException(
                        "[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                                + "] The expected request parameter type is GPT35TurboRequest.Message or List<GPT35TurboRequest.Message> when use gpt35turbo model. ");
            }
            Type act = actTypes[0];
            if (!GPT35TurboRequest.Message.class.getTypeName().equals(act.getTypeName())){
                throw new IllegalArgumentException("[" + method.getDeclaringClass().getSimpleName() + "#" + method.getName()
                        + "] The expected request parameter type is GPT35TurboRequest.Message or List<GPT35TurboRequest.Message> when use gpt35turbo model. ");
            }
           
        }
        
    }
    
    
}
