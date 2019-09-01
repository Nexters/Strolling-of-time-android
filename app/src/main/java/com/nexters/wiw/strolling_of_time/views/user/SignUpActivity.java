package com.nexters.wiw.strolling_of_time.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.nexters.wiw.strolling_of_time.BuildConfig;
import com.nexters.wiw.strolling_of_time.ProgressDialog;
import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.domain.UserService;
import com.nexters.wiw.strolling_of_time.dto.SignUpRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpResponseDto;
import com.nexters.wiw.strolling_of_time.views.main.MainActivity;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.Manifest.permission.INTERNET;


public class SignUpActivity extends AppCompatActivity {
    private UserService service;
    private ProgressDialog progressDialog;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextNickname;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initRetrofit();
        initUI();

    }

    @RequiresPermission(allOf={INTERNET})
    private void initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(UserService.class);
    }

    private void initUI(){
        editTextEmail = findViewById(R.id.et_sign_email);
        editTextPassword = findViewById(R.id.et_sign_pw);
        editTextNickname = findViewById(R.id.et_sign_nickname);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(v-> submit());
    }

    @RequiresPermission(allOf={INTERNET})
    private void submit(){
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        submit.setClickable(false);
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String nickname = editTextNickname.getText().toString().trim();

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(
                email, password, nickname, "");
        Call<SignUpResponseDto> result = service.signUp(signUpRequestDto);

        Intent main = new Intent(this, MainActivity.class);
        result.enqueue(new Callback<SignUpResponseDto>() {
            @Override
            public void onResponse(@NotNull Call<SignUpResponseDto> call, @NotNull Response<SignUpResponseDto> response) {
                if(response.isSuccessful()) {
                    startActivity(main);
                } else {
                    Log.d(this.getClass().getSimpleName(), "onResponse: " + response);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NotNull Call<SignUpResponseDto> call, @NotNull Throwable t) {
                Log.d(this.getClass().getSimpleName(), "onFailure: " + t.getMessage());
                progressDialog.dismiss();
                submit.setClickable(true);
            }
        });
    }
}
