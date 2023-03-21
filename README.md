
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

## Example

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

