package uz.bahadew.puzzle15.presentation.screen.rating;

public interface RatingContract {

    interface Model {

    }

    interface View {
        void init();

        void backToMenu();
    }

    interface Presenter {

        void onClickBack();
    }
}
