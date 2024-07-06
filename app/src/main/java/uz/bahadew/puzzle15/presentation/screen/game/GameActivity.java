package uz.bahadew.puzzle15.presentation.screen.game;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uz.bahadew.puzzle15.R;

public class GameActivity extends AppCompatActivity implements GameContract.View {
    private GameContract.Presenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        presenter = new GamePresenter(this);
    }
}
