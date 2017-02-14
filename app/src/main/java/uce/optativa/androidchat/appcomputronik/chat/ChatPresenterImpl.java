package uce.optativa.androidchat.appcomputronik.chat;

import org.greenrobot.eventbus.Subscribe;

import uce.optativa.androidchat.appcomputronik.chat.entities.ChatMessage;
import uce.optativa.androidchat.appcomputronik.chat.events.ChatEvent;
import uce.optativa.androidchat.appcomputronik.chat.ui.ChatView;
import uce.optativa.androidchat.appcomputronik.entities.User;
import uce.optativa.androidchat.appcomputronik.lib.EventBus;
import uce.optativa.androidchat.appcomputronik.lib.GreenRobotEventBus;


/**
 * Created by ykro.
 */
public class ChatPresenterImpl implements ChatPresenter {
    EventBus eventBus;
    ChatView chatView;
    ChatInteractor chatInteractor;
    ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView chatView){
        this.chatView = chatView;
        this.eventBus = GreenRobotEventBus.getInstance();

        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribeForChatUpates();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        chatInteractor.unSubscribeForChatUpates();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyChatListener();
        chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (chatView != null) {
            ChatMessage msg = event.getMessage();
            chatView.onMessageReceived(msg);
        }
    }

}
