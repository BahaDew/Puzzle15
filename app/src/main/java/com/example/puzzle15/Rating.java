package com.example.puzzle15;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Rating extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiting);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        MySharePreferences sharePreferences = MySharePreferences.getInstance(this);
        List<RatingData> ratingDataList = sharePreferences.getRating();
        findViewById(R.id.rank1).setVisibility(!ratingDataList.isEmpty() ? View.VISIBLE : View.GONE);
        findViewById(R.id.rank2).setVisibility(ratingDataList.size() >= 2 ? View.VISIBLE : View.GONE);
        findViewById(R.id.rank3).setVisibility(ratingDataList.size() >= 3 ? View.VISIBLE : View.GONE);
        if (!ratingDataList.isEmpty()) {
            TextView move1 = findViewById(R.id.move_r1);
            TextView time1 = findViewById(R.id.time_r1);
            TextView date1 = findViewById(R.id.date_r1);
            move1.setText("Move count: " + ratingDataList.get(0).move);
            time1.setText("Time: " + getTimeMill(ratingDataList.get(0).time));
            date1.setText(getDateMill(ratingDataList.get(0).date));
        }

        if (ratingDataList.size() >= 2) {
            TextView move2 = findViewById(R.id.move_r2);
            TextView time2 = findViewById(R.id.time_r2);
            TextView date2 = findViewById(R.id.date_r2);
            move2.setText("Move count: " + ratingDataList.get(1).move);
            time2.setText("Time: " + getTimeMill(ratingDataList.get(1).time));
            date2.setText(getDateMill(ratingDataList.get(1).date));
        }

        if (ratingDataList.size() >= 3) {
            TextView move3 = findViewById(R.id.move_r3);
            TextView time3 = findViewById(R.id.time_r3);
            TextView date3 = findViewById(R.id.date_r3);
            move3.setText("Move count: " + ratingDataList.get(2).move);
            time3.setText("Time: " + getTimeMill(ratingDataList.get(2).time));
            date3.setText(getDateMill(ratingDataList.get(2).date));
        }
    }

    private String getDateMill(long mill) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT);
        return formatter.format(mill);
    }

    private String getTimeMill(long mill) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss", Locale.ROOT);
        return formatter.format(mill);
    }
}