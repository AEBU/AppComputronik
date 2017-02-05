package uce.optativa.androidchat.appcomputronik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uce.optativa.androidchat.appcomputronik.model.User;
import uce.optativa.androidchat.appcomputronik.service.UserService;

import static uce.optativa.androidchat.appcomputronik.constants.Constants.url;


public class LoginActivity extends AppCompatActivity {
    UserService service;

    @BindView(R.id.btnSignin_login)
    Button btnSignin_login;
    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin_login)
    public void handleSignIn(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(UserService.class);

        Call<User> requestUser = service.getUserByEmailAndUserName(editTxtEmail.getText().toString(), editTxtPassword.getText().toString());
        requestUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                }else   {
                    Toast.makeText(LoginActivity.this, "Usuario No Existente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
