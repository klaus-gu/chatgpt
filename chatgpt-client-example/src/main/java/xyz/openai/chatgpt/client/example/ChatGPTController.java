package xyz.openai.chatgpt.client.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.openai.chatgpt.client.entity.GPT35TurboRequest;
import xyz.openai.chatgpt.client.entity.OpenAIResponse;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
@RestController
public class ChatGPTController {
    
    @Autowired
    private ChatGPTService chatGPTService;
    
    @PostMapping("/chat")
    public OpenAIResponse<GPT35TurboRequest.Message> chat(@RequestBody GPT35TurboRequest.Message message) {
        return chatGPTService.chat(message);
    }
}
