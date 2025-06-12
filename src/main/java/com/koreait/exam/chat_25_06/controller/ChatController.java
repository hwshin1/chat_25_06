package com.koreait.exam.chat_25_06.controller;

import com.koreait.exam.chat_25_06.ChatMessage;
import com.koreait.exam.chat_25_06.RsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
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

    public record messagesResponse(List<ChatMessage> chatMessages, long count) {}

    //fromId => 만약 채팅창이 있다고 할 때 기존 채팅창에서 대화내용을 불러오려는 번호
    public record messagesRequest(Long fromId) {}

    @GetMapping("/messages")
    @ResponseBody
    public RsData<messagesResponse> showMessages(messagesRequest req) {

        List<ChatMessage> messages = chatMessages;

        log.debug("req: {}", req);

        if (req.fromId != null) {
            // 해당 번호의 채팅 메세지가 전체 리스트의 몇번째 인덱스인지?
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == req.fromId)
                    .findFirst()
                    .orElse(-1);

            if (index != -1) {
                // 만약에 index가 -1 이 아니라면? 0번부터 index번 까지 제거한 리스트를 만든다.
                messages = messages.subList(index + 1, messages.size());
            }
        }

        return new RsData<>("S_1", "성공",
                new messagesResponse(messages, messages.size()));
    }
}
