package xyz.openai.chatgpt.client.handler;

import xyz.openai.chatgpt.client.entity.OpenAIException;
import xyz.openai.chatgpt.client.entity.OpenAIProxy;
import xyz.openai.chatgpt.client.enums.ModelTypeEnum;
import xyz.openai.chatgpt.client.setting.OpenAISetting;

import java.net.Proxy;

/**
 * Abstract handler for chatgpt request .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
abstract class AbstractGPTHandler<M,R> {
    
    protected final OpenAISetting openAISetting;
    
    public AbstractGPTHandler(OpenAISetting openAISetting) {
        this.openAISetting = openAISetting;
    }
    
    protected  Proxy getProxy() {
        Proxy proxy;
        
        if (!openAISetting.enableProxy) {
            return null;
        }
        switch (openAISetting.proxyType) {
            case HTTP:
                proxy = new OpenAIProxy(openAISetting.proxyHostname, Integer.parseInt(openAISetting.proxyPort), Proxy.Type.HTTP)
                        .build();
                break;
            case SOCKS:
                proxy = new OpenAIProxy(openAISetting.proxyHostname, Integer.parseInt(openAISetting.proxyPort), Proxy.Type.SOCKS)
                        .build();
                break;
            case DIRECT:
                proxy = new OpenAIProxy(openAISetting.proxyHostname, Integer.parseInt(openAISetting.proxyPort), Proxy.Type.DIRECT)
                        .build();
                break;
            default:
                proxy = null;
                break;
        }
        return proxy;
    }
    
    abstract ModelTypeEnum getType();
    
    abstract R handle(M... msg) throws OpenAIException;
    
}
