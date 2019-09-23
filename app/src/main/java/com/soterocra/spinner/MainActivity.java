package com.soterocra.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.soterocra.spinner.api.ApiInterface;
import com.soterocra.spinner.entities.User;
import com.soterocra.spinner.helper.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private Spinner spinner1;
    private List<User> userList;
    private Boolean firstSelected = Boolean.TRUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);

        retrofit = RetrofitConfig.getRetrofit("http://10.0.2.2:8080/");
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();

                    List<String> userNameList = new ArrayList<>(Arrays.asList(""));

                    for (User u : userList) {
                        userNameList.add(u.getName());
                    }

                    populateSpinner(userNameList);

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println(t.getStackTrace());
            }
        });

        AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!firstSelected) {


                    String item = spinner1.getSelectedItem().toString();

                    User user = null;

                    for (User u : userList) {
                        if (u.getName().equals(item)) {
                            user = u;
                        }
                    }
                    callUserActivity(user);
                } else {
                    firstSelected = Boolean.FALSE;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        spinner1.setOnItemSelectedListener(selectedListener);

    }

    private void populateSpinner(List<String> userNameList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                userNameList
        );

        spinner1.setAdapter(adapter);
    }

    private void callUserActivity(User user) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
