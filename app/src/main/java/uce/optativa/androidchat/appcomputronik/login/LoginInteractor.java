package uce.optativa.androidchat.appcomputronik.login;

/**
 * Created by Alexis on 26/12/2016.
 */

public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}