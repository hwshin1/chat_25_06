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

    public record writeMessageResponse(long id) { }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeMessageResponse> writeMessage() {
        ChatMessage chatMessage = new ChatMessage("홍길동", "안녕");
        chatMessages.add(chatMessage);
        return new RsData<>("S_1", "메세지 작성", new writeMessageResponse(chatMessage.getId()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> showMessages() {
        return new RsData<>("S_1", "성공", chatMessages);
    }
}
