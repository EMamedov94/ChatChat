package com.example.chatchat.services.message;

import com.example.chatchat.models.Chat;

import java.util.List;

public interface ChatService {

    // Create private chat
    Chat createPrivateChat(Long userId1, Long userId2);

    // Get all chats by user id
    List<Chat> getUserChats(Long userId);
}
