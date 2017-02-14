package uce.optativa.androidchat.appcomputronik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uce.optativa.androidchat.appcomputronik.login.LoginPresenter;
import uce.optativa.androidchat.appcomputronik.login.LoginPresenterImpl;
import uce.optativa.androidchat.appcomputronik.login.ui.LoginView;
import uce.optativa.androidchat.appcomputronik.model.Users;
import uce.optativa.androidchat.appcomputronik.network.ApiClient;
import uce.optativa.androidchat.appcomputronik.service.UserService;


public class LoginActivity extends AppCompatActivity  implements LoginView {

    @BindView(R.id.btnSignin)
    Button btnSignin1;

    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.btn_Signup)
    Button btnSignup;

    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }

    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        UserService userService = ApiClient.getRetrofit().create(UserService.class);

        Call<Users> requestUser = userService.getUserByEmailAndUserName(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
        requestUser.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Users user = response.body();
                    Log.d("Orden ", "getOrdenList: " + user.toString());
                    loginPresenter.validateLogin(editTxtEmail.getText().toString(),
                            editTxtPassword.getText().toString());
                    //startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario No Existente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onDestroy(){
        loginPresenter.onDestroy();
        super.onDestroy();
    }
    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));

    }

    @Override
    public void loginError(String error) {

    }

    @Override
    public void newUserSuccess() {

    }

    @Override
    public void newUserError(String error) {

    }

    @Override
    public void enableInputs() {

    }

    @Override
    public void disableInputs() {
    }

    @Override
    public void showProgress() {
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.btn_Signup)
    public void handleSignUp() {
        startActivity(new Intent(this, SignupActivity.class));
    }
}
