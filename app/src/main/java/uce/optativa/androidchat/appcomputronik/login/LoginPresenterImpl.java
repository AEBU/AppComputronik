package uce.optativa.androidchat.appcomputronik.login;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import uce.optativa.androidchat.appcomputronik.lib.EventBus;
import uce.optativa.androidchat.appcomputronik.lib.GreenRobotEventBus;
import uce.optativa.androidchat.appcomputronik.login.events.LoginEvent;
import uce.optativa.androidchat.appcomputronik.login.ui.LoginView;


/**
 * Created by Alexis on 27/12/2016.
 */

public class LoginPresenterImpl implements LoginPresenter {
     EventBus eventBus;
     LoginView loginView;
     LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.loginInteractor = new LoginInteractorImpl();
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {

        loginView=null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if  (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.checkAlreadyAuthenticated();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email,password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView !=null){
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email,password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }



    private void onFailedToRecoverSession() {
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();

        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    private void onSignInSuccess(){
        if (loginView !=null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if (loginView !=null){
            loginView.newUserSuccess();
        }
    }
    private void onSignInError(String error){
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if (loginView !=null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }
}
