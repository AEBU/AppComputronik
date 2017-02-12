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
import uce.optativa.androidchat.appcomputronik.model.User;
import uce.optativa.androidchat.appcomputronik.network.ApiClient;
import uce.optativa.androidchat.appcomputronik.service.UserService;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnSignin)
    Button btnSignin1;

    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.btn_Signup)
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        UserService userService = ApiClient.getRetrofit().create(UserService.class);

        Call<User> requestUser = userService.getUserByEmailAndUserName(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
        requestUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d("Orden ", "getOrdenList: " + user.toString());
                    startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario No Existente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.btn_Signup)
    public void handleSignUp() {
        startActivity(new Intent(this, SignupActivity.class));
    }
}
