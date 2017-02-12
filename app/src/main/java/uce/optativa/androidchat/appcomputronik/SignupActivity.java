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



public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_signup)
    public void handleSignUp(){
        String username=inputEmail.getText().toString();
        String email=inputPassword.getText().toString();

        UserService userService =ApiClient.getRetrofit().create(UserService.class);
        Call<User> requestUser = userService.setUser(new User(email,username));
        requestUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("d",response.toString());
                if (response.isSuccessful()){
                    Log.d("d","3 No funciona");

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(SignupActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("d",t.getMessage());
                Toast.makeText(SignupActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, NavigationActivity.class));

            }
        });


    }

}
