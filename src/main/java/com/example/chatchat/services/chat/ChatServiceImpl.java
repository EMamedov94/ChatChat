package com.example.chatchat.services.message;

import com.example.chatchat.models.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    @Override
    public Chat createPrivateChat(Long userId1, Long userId2) {
        return null;
    }

    @Override
    public List<Chat> getUserChats(Long userId) {
        return List.of();
    }
}
