package uce.optativa.androidchat.appcomputronik.chat.ui;


import uce.optativa.androidchat.appcomputronik.chat.entities.ChatMessage;

/**
 * Created by ykro.
 */
public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
