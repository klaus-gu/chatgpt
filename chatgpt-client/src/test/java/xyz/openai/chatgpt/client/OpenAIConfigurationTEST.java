package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.config.OpenAIConfiguration;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class OpenAIConfigurationTEST {
    
    public static void main(String[] args) {
        OpenAIConfiguration openAIConfiguration = OpenAIConfiguration.getInstance();
        System.out.println(openAIConfiguration.getApiKey());
        OpenAIConfiguration openAIConfiguration1 = OpenAIConfiguration.getInstance();
        openAIConfiguration1.setApiKey("111111");
        OpenAIConfiguration.update(openAIConfiguration1);
        System.out.println(openAIConfiguration.getApiKey());
    }
}
