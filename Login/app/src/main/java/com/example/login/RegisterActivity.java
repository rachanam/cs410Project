package com.example.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.data.LoginDataSource;
import com.example.login.data.LoginRepository;
import com.example.login.data.Result;
import com.example.login.data.model.LoggedInUser;
import com.example.login.data.model.NewUser;
import com.example.login.ui.login.LoginActivity;
import com.example.login.ui.login.LoginViewModel;
import com.example.login.ui.login.LoginViewModelFactory;

public class RegisterActivity extends AppCompatActivity {
    private LoginRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        repository = LoginRepository.getInstance(new LoginDataSource());

        final EditText firstLastNameEditText = findViewById(R.id.firstLastName);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.txtPassword);
        final EditText emailEditText = findViewById(R.id.emailAddress);
        final EditText phoneNumberEditText = findViewById(R.id.phoneNumber);
        final Button submitButton = findViewById(R.id.btnSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result<LoggedInUser> result = repository.register(new NewUser(
                        firstLastNameEditText.getText().toString(),
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        phoneNumberEditText.getText().toString()
                ));

                if (result instanceof Result.Success) {
                    updateUiWithUser(((Result.Success<LoggedInUser>) result).getData());
                }
            }
        });
    }

    private void updateUiWithUser(LoggedInUser loggedInUser) {
        String welcome = getString(R.string.welcome) + loggedInUser.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
