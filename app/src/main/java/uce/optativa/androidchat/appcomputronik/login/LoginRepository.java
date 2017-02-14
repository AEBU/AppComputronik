package uce.optativa.androidchat.appcomputronik.login;

/**
 * Created by Alexis on 26/12/2016.
 */

public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkAlreadyAuthenticated();
}
