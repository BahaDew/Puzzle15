package uz.bahadew.puzzle15.presentation.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import uz.bahadew.puzzle15.R;

public class ExitDialog extends DialogFragment {

    private OnClickYes onClickYes;
    private OnClickNo onClickNo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_exit, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_yes).setOnClickListener(v -> onClickYes.click());
        view.findViewById(R.id.btn_no).setOnClickListener(v -> onClickNo.click());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @FunctionalInterface
    public interface OnClickYes {
        void click();
    }

    @FunctionalInterface
    public interface OnClickNo {
        void click();
    }

    public void setOnClickYes(OnClickYes onClickYes) {
        this.onClickYes = onClickYes;
    }

    public void setOnClickNo(OnClickNo onClickNo) {
        this.onClickNo = onClickNo;
    }
}