package uce.optativa.androidchat.appcomputronik.chat;


import uce.optativa.androidchat.appcomputronik.chat.events.ChatEvent;

/**
 * Created by ykro.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);


}
