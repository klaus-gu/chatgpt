package xyz.openai.chatgpt.client;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class RequestProviderTEST {
    
    public static void main(String[] args) {
        RequestProvider provider = new RequestProvider().create("who are u?");
        System.out.println(provider.getData());
    }
}
