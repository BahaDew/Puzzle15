package uz.bahadew.puzzle15.presentation.screen.rating;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import uz.bahadew.puzzle15.R;

public class RatingActivity extends AppCompatActivity implements RatingContract.View {

    private RatingContract.Presenter presenter;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        presenter = new RatingPresenter(this);
        btnBack.animate();
    }

    @Override
    public void init() {
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> presenter.onClickBack());
    }

    @Override
    public void backToMenu() {
        finish();
    }
}