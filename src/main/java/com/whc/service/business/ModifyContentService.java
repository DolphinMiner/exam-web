package com.whc.service.business;

import com.mysql.cj.util.StringUtils;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import com.whc.data.dto.ModifyContentRequest;
import com.whc.data.dto.ModifyContentResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-30 09:01
 * @Description:
 */
public class ModifyContentService {

    private static final String APK_KEY = "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";

    // regex
    private static final String PATTERN = "^[a-zA-Z0-9!@#$%^&*()\\-_=+{}\\[\\]|;:'\",.<>/?`~\\s]*$\n";

    public ModifyContentResponse process(ModifyContentRequest request) {
        // build res
        ModifyContentResponse res = this.buildFailResponse("process failed");
        // validate
        if (request == null) {
            return this.buildFailResponse("request should not be null");
        }
        if (StringUtils.isNullOrEmpty(request.getPrompt())) {
            return this.buildFailResponse("prompt should not be empty");
        }
        if (!Pattern.matches(PATTERN, request.getPrompt())) {
            return this.buildFailResponse("prompt should only include English,blank and specific character");
        }
        try {
            // process
            OpenAiService service = new OpenAiService(APK_KEY);
            final List<ChatMessage> messages = new ArrayList<>();
            final ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), request.getPrompt());
            messages.add(systemMessage);
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .n(5)
                    .maxTokens(50)
                    .logitBias(new HashMap<>())
                    .build();
            StringBuilder answer = new StringBuilder();
            List<ChatCompletionChoice> choices = service.createChatCompletion(chatCompletionRequest).getChoices();
            // convert answer
            choices.forEach(choice -> {
                String content = choice.getMessage().getContent();
                if (answer.indexOf(content) == -1) {
                    answer.append(content);
                }
            });
            res = this.buildSuccessfulResponse(answer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private ModifyContentResponse buildFailResponse(String message) {
        ModifyContentResponse res = new ModifyContentResponse();
        res.setCode(1);
        res.setMessage("process failed");
        return res;
    }

    private ModifyContentResponse buildSuccessfulResponse(String answer) {
        ModifyContentResponse res = new ModifyContentResponse();
        res.setCode(0);
        res.setMessage("process successful");
        res.setAnswer(answer);
        return res;
    }
}
