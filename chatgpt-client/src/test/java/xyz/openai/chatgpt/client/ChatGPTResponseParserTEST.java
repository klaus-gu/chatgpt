package xyz.openai.chatgpt.client;

import xyz.openai.chatgpt.client.parser.ChatGPTResponseParser;

/**
 * TODO .
 * @author <a href="mailto:guyue375@outlook.com">Klaus.turbo</a>
 * @program chatgpt
 **/
public class ChatGPTResponseParserTEST {
    private static final String res = "{\n" + "    \"id\": \"chatcmpl-6uEcFsdssn1pJJXMMjS4gIETUcRgu\",\n"
            + "    \"object\": \"chat.completion\",\n" + "    \"created\": 1678860571,\n"
            + "    \"model\": \"gpt-3.5-turbo-0301\",\n" + "    \"usage\": {\n" + "        \"prompt_tokens\": 10,\n"
            + "        \"completion_tokens\": 32,\n" + "        \"total_tokens\": 42\n" + "    },\n"
            + "    \"choices\": [\n" + "        {\n" + "            \"message\": {\n"
            + "                \"role\": \"assistant\",\n"
            + "                \"content\": \"\\n\\nI am an AI language model created by OpenAI, designed to assist with various tasks such as answering questions, generating text, and performing natural language processing.\"\n"
            + "            },\n" + "            \"finish_reason\": \"stop\",\n" + "            \"index\": 0\n"
            + "        }\n" + "    ]\n" + "}";
    
    public static void main(String[] args) {
        final ChatGPTResponseParser.ParseResult parseResult = ChatGPTResponseParser.parseGPT35Turbo(res);
        parseResult.getHtml();
    }
}
