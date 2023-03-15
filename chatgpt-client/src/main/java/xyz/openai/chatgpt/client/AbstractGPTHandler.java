package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.config.OpenAIConfiguration;
import xyz.openai.chatgpt.client.model.OpenAIProxy;

import java.net.Proxy;

/**
 * Abstract handler for chatgpt request .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public abstract class AbstractGPTHandler {
    
    protected static Proxy getProxy() {
        Proxy proxy;
        OpenAIConfiguration instance = OpenAIConfiguration.getInstance();
        if (!instance.getEnableProxy()) {
            return null;
        }
        switch (instance.proxyType) {
            case HTTP:
                proxy = new OpenAIProxy(instance.getProxyHostname(), Integer.parseInt(instance.getProxyPort()),
                        Proxy.Type.HTTP).build();
                break;
            case SOCKS:
                proxy = new OpenAIProxy(instance.getProxyHostname(), Integer.parseInt(instance.getProxyPort()),
                        Proxy.Type.SOCKS).build();
                break;
            case DIRECT:
                proxy = new OpenAIProxy(instance.getProxyHostname(), Integer.parseInt(instance.getProxyPort()),
                        Proxy.Type.DIRECT).build();
                break;
            default:
                proxy = null;
                break;
        }
        return proxy;
    }
    
    public abstract <T> T handle(String question);
    
}
