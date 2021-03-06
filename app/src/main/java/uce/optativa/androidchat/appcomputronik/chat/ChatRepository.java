package uce.optativa.androidchat.appcomputronik.chat;

/**
 * Created by ykro.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setReceiver(String receiver);

    void destroyChatListener();
    void subscribeForChatUpates();
    void unSubscribeForChatUpates();

    void changeUserConnectionStatus(boolean online);
}
