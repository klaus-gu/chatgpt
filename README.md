
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
