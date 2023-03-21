# ChatGPT Client

## Example

```
// step1 : init setting
OpenAISetting openAISetting = new OpenAISetting();

// step2 : setup apiKey in Authorization-Mode : ApiKey
openAISetting.apiKey="sk-*******VFN";

//step3 :  build request
final OpenAIResponse handle = OpenAI.ChatGPT.ChatGPT35Turbo.config(openAISetting)
.handle(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you ?"));

//step4 : see result
System.out.println(JSON.toJSONString(handle));
```

# ChatGPT Client Spring

## Simple Conversation Example

#### Step One： Define an OpenAISettingFactory for OpenAISetting config

```
public class MyOpenAISettingFactory implements OpenAISettingFactory {
  
    @Override
    public OpenAISetting getSetting() {
        final OpenAISetting openAISetting = new OpenAISetting();
        openAISetting.apiKey = "sk-7ip********************EqU3AU";
        return openAISetting;
    }
}
```

#### Step Two：Define user interface

```
@ChatGPTClient(settingFactory = MyOpenAISettingFactory.class)
public interface ChatGPTContextService {
  
    @GPT35Turbo(enableContext = true)
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
  
    @GPT35Turbo
    OpenAIResponse<GPT35TurboRequest.Message> chat(List<GPT35TurboRequest.Message> message);
}
```

#### Step Three：Use Scanner to find user interface

```
@ChatGPTScan(basePackages = "xyz.openai.chatgpt.client.spring.service")
public class ChatGPTServiceConfigurerTEST {
  
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ChatGPTServiceConfigurerTEST.class);
        applicationContext.refresh();
        ChatGPTService01 chatGPTService01 = applicationContext.getBean(ChatGPTService01.class);
        final OpenAIResponse<GPT35TurboRequest.Message> chat = chatGPTService01
                .chat(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you?"));
        System.out.println(JSON.toJSONString(chat));
        applicationContext.close();
    }
}
```

## Enable Conversation Context Example

#### Follow the step above , we need to enable it by set enableContext=true in annotation @GPT35Turbo

```
@GPT35Turbo(enableContext = true, conversationMapperFactory = MyDefaultGPT35TurboConversationMapperFactory.class)
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
```

#### After this ,we can chat with gpt-35-turbo model with context keeped.

#### Also your can define your own ConversantionMapper by set a factory in annotation @GTP35Turbo

```
 @GPT35Turbo(enableContext = true, conversationMapperFactory = MyDefaultGPT35TurboConversationMapperFactory.class)
    OpenAIResponse<GPT35TurboRequest.Message> chat(GPT35TurboRequest.Message message);
```

#### If not , a default ConversationMapper will be used.

#### To define a ConversationMapper ,you should do as below

```
public class MyDefaultGPT35TurboConversationMapperFactory
        implements ConversationMapperFactory<GPT35TurboRequest.Message> {
  
    private final ConversationMapper<GPT35TurboRequest.Message> conversationMapper = new MyDefaultGPT35TurboConversationMapper();
  
    public MyDefaultGPT35TurboConversationMapperFactory() {
    }
  
    @Override
    public ConversationMapper<GPT35TurboRequest.Message> getConversationMapper() {
        return conversationMapper;
    }
  
    public static class MyDefaultGPT35TurboConversationMapper implements ConversationMapper<GPT35TurboRequest.Message> {
  
        private final ConcurrentHashMap<String, List<GPT35TurboRequest.Message>> conversationContextMap = new ConcurrentHashMap<>();
  
        public MyDefaultGPT35TurboConversationMapper() {
        }
  
        @Override
        public List<GPT35TurboRequest.Message> getContext(String conversationId) {
            return conversationContextMap.get(conversationId);
        }
  
        @Override
        public List<GPT35TurboRequest.Message> appendContext(String conversationId,
                List<GPT35TurboRequest.Message> newContexts) {
            final List<GPT35TurboRequest.Message> oldContext = getContext(conversationId);
            if (oldContext == null) {
                newContexts.forEach(message -> message.setConversationId(conversationId));
                conversationContextMap.put(conversationId,newContexts);
            }else {
                oldContext.addAll(newContexts);
                oldContext.forEach(message -> message.setConversationId(conversationId));
                conversationContextMap.put(conversationId,oldContext);
            }
            return getContext(conversationId);
        }
    }
}
```

#### You can define your own code in method getContext and appendContext.

# Integrate with springboot

[https://github.com/klaus-gu/chatgpt/tree/master/chatgpt-client-example/src/main/java/xyz/openai/chatgpt/client/example](https://)
