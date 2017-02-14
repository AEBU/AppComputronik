package uce.optativa.androidchat.appcomputronik.chat;

/**
 * Created by ykro.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void destroyChatListener();
    void subscribeForChatUpates();
    void unSubscribeForChatUpates();
}
