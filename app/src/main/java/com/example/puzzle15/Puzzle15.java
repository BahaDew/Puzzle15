package com.example.puzzle15;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puzzle15 extends AppCompatActivity {
    private final List<String> values = new ArrayList<>(Arrays.asList(
            "1", "2", "3", "4",
            "5", "6", "7", "8",
            "9", "10", "11", "12",
            "13", "14", "15", ""
    ));
    private final int[] numbers = new int[16];
    private long time = -1;
    private final int N = (int) Math.sqrt(numbers.length);
    private int positionSpace = values.indexOf("");
    private MediaPlayer mediaPlayer;

    private boolean isVolume = false;

    private WinDialogFr dialogFragment;

    private final AppCompatButton[] appCompatButtons = new AppCompatButton[16];
    private TextView moveTxt;
    private Chronometer chronometerTex;
    private MySharePreferences mysharePreferences;
    private int moveCount = 0;
    private boolean message = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST1", "onCreate ishladi!!!");
        mysharePreferences = MySharePreferences.getInstance(this);
        setContentView(R.layout.puzzle_15);
        moveTxt = findViewById(R.id.move_text);
        chronometerTex = findViewById(R.id.chronometer_text);
        changeLightStatusBar();
        Intent resIntent = getIntent();
        mediaPlayer = MediaPlayer.create(this, R.raw.plump_btn_click);
        isVolume = mysharePreferences.getBoolean("VOLUME", true);
        ImageView btnVolume = findViewById(R.id.btn_volume);
        btnVolume.setImageResource(isVolume ? R.drawable.ic_volume_svgrepo_com : R.drawable.ic_volume_off_svgrepo_com);
        btnVolume.setOnClickListener(v -> {
            isVolume = !isVolume;
            mysharePreferences.myPut("VOLUME", isVolume);
            btnVolume.setImageResource(isVolume ? R.drawable.ic_volume_svgrepo_com : R.drawable.ic_volume_off_svgrepo_com);
        });
        if (resIntent != null && resIntent.getExtras() != null) {
            message = resIntent.getExtras().getBoolean("POS", true);
            resIntent.putExtra("POS", true);
        }
        Log.d("BBB", "message" + message);
        if (message) {
            String state = mysharePreferences.getString("buttons_state", "$");
            moveCount = mysharePreferences.getInt("move_count", 0);
            long timeState = mysharePreferences.getLong("time_state", 0);
            chronometerTex.setBase(SystemClock.elapsedRealtime() - timeState);
            moveTxt.setText(String.valueOf(moveCount));
            if (state.equals("$")) {
                firstLoad();
            } else {
                String[] buttonsState = state.split("[$]");
                for (int i = 0; i < buttonsState.length; i++) {
                    values.set(i, buttonsState[i]);
                }
                loadAppButton();
                positionSpace = values.indexOf("");
                setTextButton();
                setClickButtons();
            }
            if (!isWin()) {
                chronometerTex.start();
            }
        } else {
            chronometerTex.setBase(SystemClock.elapsedRealtime());
            chronometerTex.start();
            firstLoad();
        }
        findViewById(R.id.home_puzzle).setOnClickListener(v -> finish());
        putContinueState(!isWin());
        dialogFragment = new WinDialogFr();
        dialogFragment.setCancelable(false);
        dialogFragment.setOnClickHome(() -> {
            dialogFragment.dismiss();
            putContinueState(false);
            finish();
        });
        dialogFragment.setOnClickRefresh(() -> {
            dialogFragment.dismiss();
            shuffle();
            chronometerTex.setBase(SystemClock.elapsedRealtime());
            chronometerTex.start();
            moveCount = 0;
            moveTxt.setText(String.valueOf(moveCount));
            putContinueState(true);
        });
    }

    @Override
    protected void onStart() {
        Log.d("TEST1", "onStart ishladi!!!");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("TEST1", "onRestart ishladi!!!");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("TEST1", "onDestroy ishladi!!!");
        super.onDestroy();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onStop() {
        super.onStop();
        StringBuilder sb = new StringBuilder();
        chronometerTex.stop();
        for (AppCompatButton appCompatButton : appCompatButtons) {
            sb.append(appCompatButton.getText()).append("$");
        }
        mysharePreferences.myPut("move_count", moveCount);
        mysharePreferences.myPut("buttons_state", sb.toString());
        mysharePreferences.myPut("this_state", true);
    }

    @Override
    protected void onResume() {
        Log.d("TEST1", "onResume ishladi!!!");
        if (message) {
            long timeState = mysharePreferences.getLong("time_state", 0);
            chronometerTex.setBase(SystemClock.elapsedRealtime() - timeState);
        }
        if (!isWin()) {
            chronometerTex.start();
        } else {
            if (dialogFragment != null) {
                Dialog dialog = dialogFragment.getDialog();
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                } else {
                    dialogFragment.show(getSupportFragmentManager(), "");
                }
            }
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (dialogFragment != null) {
            Dialog dialog = dialogFragment.getDialog();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialogFragment.dismiss();
            }
        }
        chronometerTex.stop();
        if (!isWin()) {
            mysharePreferences.myPut("time_state", SystemClock.elapsedRealtime() - chronometerTex.getBase());
        }
        Log.d("TEST1", "onPause ishladi!!!");
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        Log.d("TEST1", "onPostResume ishladi!!!");
        super.onPostResume();
    }

    private void loadAppButton() {
        RelativeLayout view = findViewById(R.id.relative_puzzle);
        for (int i = 0; i < appCompatButtons.length; i++) {
            appCompatButtons[i] = (AppCompatButton) view.getChildAt(i);
        }
    }

    private void setClickButtons() {
        for (int i = 0; i < appCompatButtons.length; i++) {
            int index = i;
            appCompatButtons[i].setOnClickListener(v -> onClick((AppCompatButton) v, index));
        }
    }

    public void onClick(AppCompatButton v, int index) {
        if (time == -1 || System.currentTimeMillis() - time >= 200) {
            time = System.currentTimeMillis();
            int vX = index / N;
            int vY = index % N;
            int spaceX = positionSpace / N;
            int spaceY = positionSpace % N;
            boolean isMove = ((Math.abs(vX - spaceX) == 1 && Math.abs(vY - spaceY) == 0)
                    || (Math.abs(vX - spaceX) == 0 && Math.abs(vY - spaceY) == 1));
            if (isMove) {
                if (isVolume) {
                    mediaPlayer.start();
                }
                appCompatButtons[positionSpace].setText(v.getText());
                if (!correctIndex(positionSpace)) {
                    appCompatButtons[positionSpace].setBackgroundResource(R.drawable.puzzle_button);
                }
                appCompatButtons[index].setText("");
                appCompatButtons[index].setBackgroundResource(R.drawable.puzzle_space_backround);
                positionSpace = index;
                moveTxt.setText(String.valueOf(++moveCount));
            }
            if (positionSpace == 15) {
                winGame();
            }
            time = 0;
        }
    }

    private void winGame() {
        if (isWin()) {
            chronometerTex.stop();
            mysharePreferences.putRating(SystemClock.elapsedRealtime() - chronometerTex.getBase(), moveCount);
            mysharePreferences.myPut("time_state", SystemClock.elapsedRealtime() - chronometerTex.getBase());
            if (dialogFragment != null) {
                Dialog dialog = dialogFragment.getDialog();
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                } else {
                    dialogFragment.show(getSupportFragmentManager(), "");
                }
            }
        }
    }

    private void setTextButton() {
        for (int i = 0; i < appCompatButtons.length; i++) {
            appCompatButtons[i].setText(values.get(i));
            correctIndex(i);
        }
        positionSpace = values.indexOf("");
        appCompatButtons[positionSpace].setBackgroundResource(R.drawable.puzzle_space_backround);
    }

    public boolean isWin() {
        for (int i = 0; i < appCompatButtons.length - 1; i++) {
            if (!correctIndex(i)) {
                return false;
            }
        }
        return true;
    }

    private void shuffle() {
        do {
            Collections.shuffle(values);
            valuesToNumber();
            Log.d("TEST1", "Bumaydi!!!");
        } while (!isSolvable());
        setTextButton();
    }

    private boolean isSolvable() {
        int countInv = countInv();
        if (N % 2 == 1) {
            return countInv % 2 == 0;
        } else {
            /*
             * if(positionSpace % 2 == 1){
                return countInv % 2 == 0;
            } else {
                return countInv % 2 == 1;
            }
             */
            return (countInv % 2) != ((N - positionSpace / N) % 2);
        }
    }

    private int countInv() {
        int count = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] != 0 && numbers[j] != 0 && numbers[i] > numbers[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void valuesToNumber() {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).isEmpty()) {
                numbers[i] = 0;
                positionSpace = i;
            } else {
                numbers[i] = Integer.parseInt(values.get(i));
            }
        }
    }

    private void firstLoad() {
        loadAppButton();
        setClickButtons();
        shuffle();
    }

    private boolean correctIndex(int index) {
        if (appCompatButtons[index].getText().equals(Integer.toString(index + 1))) {
            appCompatButtons[index].setBackgroundResource(R.drawable.puzzle_correct_button_backround);
            return true;
        } else {
            if (index == positionSpace) return false;
            appCompatButtons[index].setBackgroundResource(R.drawable.puzzle_button);
        }
        return false;
    }

    private void putContinueState(boolean bool) {
        String continueState = "continueState";
        mysharePreferences.myPut(continueState, bool);
    }

    private void changeLightStatusBar() {
        WindowInsetsControllerCompat wic = new WindowInsetsControllerCompat(this.getWindow(), this.getWindow().getDecorView());
        wic.setAppearanceLightStatusBars(true);
    }
}
