package uz.bahadew.puzzle15.presentation.screen.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import uz.bahadew.puzzle15.R;
import uz.bahadew.puzzle15.presentation.screen.game.GameActivity;
import uz.bahadew.puzzle15.presentation.screen.info.InfoActivity;
import uz.bahadew.puzzle15.presentation.screen.rating.RatingActivity;

public class MenuActivity extends AppCompatActivity implements MenuContract.View {

    private TextView btnContinue, btnNewGame, btnRating, btnInfo;
    private MenuContract.Presenter presenter;
    private final String continueState = "CONTINUE_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        presenter = new MenuPresenter(this);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void init() {
        btnContinue = findViewById(R.id.btn_continue);
        btnNewGame = findViewById(R.id.btn_new_game);
        btnRating = findViewById(R.id.btn_rating);
        btnInfo = findViewById(R.id.btn_info);
        btnContinue.setOnClickListener(v -> presenter.onClickContinue());
        btnInfo.setOnClickListener(v -> presenter.onClickInfo());
        btnRating.setOnClickListener(v -> presenter.onClickRating());
        btnNewGame.setOnClickListener(v -> presenter.onClickNewGame());
    }

    @Override
    public void goContinue() {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(continueState, true);
        startActivity(intent, bundle);
    }

    @Override
    public void goInfo() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void goRating() {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }

    @Override
    public void goNewGame() {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(continueState, false);
        startActivity(intent);
    }

    @Override
    public void ratingVisibleState(boolean state) {
        btnRating.setVisibility(state ? TextView.VISIBLE : TextView.GONE);
    }

    @Override
    public void continueVisibleState(boolean state) {
        btnContinue.setVisibility(state ? TextView.VISIBLE : TextView.GONE);
    }
}