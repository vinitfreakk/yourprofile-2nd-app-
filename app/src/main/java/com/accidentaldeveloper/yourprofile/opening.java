package com.accidentaldeveloper.yourprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class opening extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
    }

    public void jump7(View view) {
        Intent jump7 = new Intent(this,MainActivity.class);
        startActivity(jump7);
    }
}