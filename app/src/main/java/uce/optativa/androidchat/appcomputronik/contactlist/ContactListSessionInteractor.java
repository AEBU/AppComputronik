package uce.optativa.androidchat.appcomputronik.contactlist;

/**
 * Created by Alexis on 29/12/2016.
 */

public interface ContactListSessionInteractor {

    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);

}
