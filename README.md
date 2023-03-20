# ChatGPT Client

## Use case

// init setting

OpenAISetting openAISetting = new OpenAISetting();

// setup apiKey in Authorization-Mode : ApiKey
openAISetting.apiKey="sk-*******VFN";

// build request
final OpenAIResponse handle = OpenAI.ChatGPT.ChatGPT35Turbo.config(openAISetting)
.handle(new GPT35TurboRequest.Message(RoleEnum.USER.getRoleName(), "who are you ?"));

// see result
System.out.println(JSON.toJSONString(handle));



# ChatGPT Client Spring

## Use case
