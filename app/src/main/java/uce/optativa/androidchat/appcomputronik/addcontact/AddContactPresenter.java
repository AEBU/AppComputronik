package uce.optativa.androidchat.appcomputronik.addcontact;


import uce.optativa.androidchat.appcomputronik.addcontact.events.AddContactEvent;

/**
 * Created by ykro.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}

