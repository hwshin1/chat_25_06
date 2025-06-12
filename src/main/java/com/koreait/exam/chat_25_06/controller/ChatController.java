package com.koreait.exam.chat_25_06.controller;

import com.koreait.exam.chat_25_06.ChatMessage;
import com.koreait.exam.chat_25_06.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    List<ChatMessage> chatMessages =  new ArrayList<>();

    public record writeChatMessageResponse(long id, String authorName, String content) {}

    public record writeChatMessageRequest(String authorName, String content) {}

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeChatMessageResponse> writeMessage(@RequestBody writeChatMessageRequest req) {
        ChatMessage chatMessage = new ChatMessage(req.authorName, req.content);
        chatMessages.add(chatMessage);
        return new RsData<>("S_1", "메세지 작성", new writeChatMessageResponse(chatMessage.getId(), chatMessage.getAuthorName(), chatMessage.getContent()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> showMessages() {
        return new RsData<>("S_1", "성공", chatMessages);
    }
}
