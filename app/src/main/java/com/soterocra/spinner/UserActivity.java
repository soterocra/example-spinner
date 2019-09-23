package com.soterocra.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.soterocra.spinner.entities.User;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        TextView specificUser = findViewById(R.id.specificUser);
        specificUser.setText(user.toString());
    }
}
