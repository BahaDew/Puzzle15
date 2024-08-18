package com.example.puzzle15;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;


public class WinDialogFr extends DialogFragment {
    private ClickHome onClickHome;
    private ClickRefresh onClickRefresh;

    public WinDialogFr() {
        super(R.layout.dialog_win);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_home).setOnClickListener(v -> {
            onClickHome.listener();
        });
        view.findViewById(R.id.btn_refresh).setOnClickListener(v -> {
            onClickRefresh.listener();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setBackgroundDrawableResource(R.drawable.bg_dialog_win);
    }

    public void setOnClickHome(ClickHome onClickHome) {
        this.onClickHome = onClickHome;
    }

    public void setOnClickRefresh(ClickRefresh onClickRefresh) {
        this.onClickRefresh = onClickRefresh;
    }
}
