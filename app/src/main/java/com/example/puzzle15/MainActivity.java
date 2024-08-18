package com.example.puzzle15;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowInsetsControllerCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private long time = System.currentTimeMillis();
    private TextView continueTextView;
    private TextView ratingBtn;
    private MySharePreferences mySharePreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BAHA", "onCreate: ");
        setContentView(R.layout.activity_main);
        mySharePreferences = MySharePreferences.getInstance(this);
        ratingBtn = findViewById(R.id.rating);
        TextView infoTextView = findViewById(R.id.info);
        continueTextView = findViewById(R.id.continue_btn);
        TextView newGameTextView = findViewById(R.id.new_game);
        checkContinueState();
        checkRatingState();
        changeLightStatusBar(false);
        ratingBtn.setOnClickListener(v -> toRating());
        infoTextView.setOnClickListener(v -> {
                    if (System.currentTimeMillis() - time >= 500) {
                        time = System.currentTimeMillis();
                        startActivity(new Intent(MainActivity.this, Info.class));
                    }
                }
        );
        newGameTextView.setOnClickListener(v -> getIntent(false));

        continueTextView.setOnClickListener(v -> getIntent(true));
    }

    private void getIntent(boolean pos) {
        if (System.currentTimeMillis() - time >= 500) {
            time = System.currentTimeMillis();
            Intent intent = new Intent(MainActivity.this, Puzzle15.class);
            intent.putExtra("POS", pos);
            startActivity(intent);
        }
    }

    private void toRating() {
        if (System.currentTimeMillis() - time >= 500) {
            time = System.currentTimeMillis();
            Intent intent = new Intent(MainActivity.this, Rating.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("BAHA", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkContinueState();
        changeLightStatusBar(false);
        checkRatingState();
        Log.d("BAHA", "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("BAHA", "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("BAHA", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BAHA", "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("BAHA", "onPause: ");
    }

    private void checkContinueState() {
        String continueState = "continueState";
        boolean state = mySharePreferences.getBoolean(continueState, false);
        continueTextView.setVisibility((state ? View.VISIBLE : View.GONE));
    }
    private void changeLightStatusBar(boolean bool) {
        WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(this.getWindow(), this.getWindow().getDecorView());
        wic.setAppearanceLightStatusBars(bool);
    }
    private void checkRatingState() {
        boolean bool = mySharePreferences.getRating().isEmpty();
        ratingBtn.setVisibility(bool ? View.GONE : View.VISIBLE);
    }
}