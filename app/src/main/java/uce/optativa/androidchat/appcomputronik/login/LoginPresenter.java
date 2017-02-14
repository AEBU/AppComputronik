package uce.optativa.androidchat.appcomputronik.login;


import uce.optativa.androidchat.appcomputronik.login.events.LoginEvent;

/**
 * Created by Alexis on 26/12/2016.
 */

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);



}
