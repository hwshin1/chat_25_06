package com.koreait.exam.chat_25_06.controller;

import com.koreait.exam.chat_25_06.ChatMessage;
import com.koreait.exam.chat_25_06.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatController {

    ChatMessage chatMessage = new ChatMessage("dd", "ss");

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage() {
        return new  RsData<>("S_1", "메세지 작성", chatMessage);
    }
}
