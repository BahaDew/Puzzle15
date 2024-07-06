package uz.bahadew.puzzle15.presentation.screen.info;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import uz.bahadew.puzzle15.R;

public class InfoActivity extends AppCompatActivity implements InfoContract.View {

    private InfoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        presenter = new InfoPresenter(this);
    }
}