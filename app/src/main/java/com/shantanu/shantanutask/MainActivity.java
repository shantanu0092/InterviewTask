package com.shantanu.shantanutask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText usernames, password;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernames = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_userid = usernames.getText().toString();
                String pass = password.getText().toString();

                if (email_userid.isEmpty()) {
                    usernames.setError("Email is required!");
                    password.requestFocus();

                } else if (pass.isEmpty()) {
                    usernames.setError("Password is required!");
                    password.requestFocus();

                } else {
                    login(email_userid, pass);
                }

            }
        });

    }


    private void login(String email, String pass) {

        ApiInterface apiInterface = Apis.postApiClient().create(ApiInterface.class);
        Call<LoginModel> call = apiInterface.login(email, pass, "1");

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    LoginModel loginModel = response.body();
                } else {

                    Toast.makeText(MainActivity.this, "Server Connection Failed!", Toast.LENGTH_SHORT).show();
                }


                call.cancel();
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                call.cancel();
            }
        });

    }

}
