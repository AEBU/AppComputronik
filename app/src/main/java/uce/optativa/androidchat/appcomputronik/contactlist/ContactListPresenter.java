package uce.optativa.androidchat.appcomputronik.contactlist;


import uce.optativa.androidchat.appcomputronik.contactlist.events.ContactListEvent;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);



}
