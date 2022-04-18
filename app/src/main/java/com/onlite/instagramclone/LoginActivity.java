package com.onlite.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnSignUpChangeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnLogin);
                }
                return false;
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUpChangeActivity = findViewById(R.id.btnSignUpChangeActivity);

        btnLogin.setOnClickListener(this);
        btnSignUpChangeActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (edtPassword.getText().toString().equals("") && edtEmail.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Please enter email and password!", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                } else if (edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Password is Required!", FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                } else if (edtEmail.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Email is Required!", Toast.LENGTH_SHORT, FancyToast.INFO, true).show();
                } else {
                    ParseUser.logInInBackground(edtEmail.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.getUsername() + "is logged in!", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUpChangeActivity:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLoginLayout(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}