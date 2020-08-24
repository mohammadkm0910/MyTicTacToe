package com.mohammadkk.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void twoPeopleActivityLoad(View view) {
        Intent intent = new Intent(MainActivity.this,TwoPeopleGameActivity.class);
        startActivity(intent);
    }

    public void onePeopleActivityLoad(View view) {
        Intent intent = new Intent(MainActivity.this,OnePeopleGameActivity.class);
        startActivity(intent);
    }
}