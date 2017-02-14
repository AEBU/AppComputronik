package uce.optativa.androidchat.appcomputronik.chat.events;


import uce.optativa.androidchat.appcomputronik.chat.entities.ChatMessage;

/**
 * Created by ykro.
 */
public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
