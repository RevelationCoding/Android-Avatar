package com.example.androidavatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mylibrary.AndroidAvatar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidAvatar androidAvatar;
        androidAvatar =findViewById(R.id.avatarBack_random);
        androidAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidAvatar.setRandomColor();
            }
        });
    }
}