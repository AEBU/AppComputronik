package uce.optativa.androidchat.appcomputronik.contactlist.ui;


import uce.optativa.androidchat.appcomputronik.entities.User;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListView {
    void onContactAdded(User user);
    void  onContactChanged(User user);
    void onContactRemoved(User user);
}
