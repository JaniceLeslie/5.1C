package com.example.a51c;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {

    public SignupFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        EditText fullNameField = view.findViewById(R.id.editTextFullName);
        EditText usernameField = view.findViewById(R.id.editTextNewUsername);
        EditText passwordField = view.findViewById(R.id.editTextNewPassword);
        EditText confirmPasswordField = view.findViewById(R.id.editTextConfirmPassword);
        Button signupButton = view.findViewById(R.id.buttonSignup);

        signupButton.setOnClickListener(v -> {
            String fullName = fullNameField.getText().toString().trim();
            String username = usernameField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmPassword = confirmPasswordField.getText().toString().trim();

            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("fullName", fullName);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(getContext(), "Account created successfully!", Toast.LENGTH_SHORT).show();

            // Go back to Login screen
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}

