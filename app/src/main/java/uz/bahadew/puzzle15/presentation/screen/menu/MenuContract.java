package uz.bahadew.puzzle15.presentation.screen.menu;

public interface MenuContract {

    interface Model {

    }

    interface View {
        void init();

        void goContinue();

        void goInfo();

        void goRating();

        void goNewGame();

        void ratingVisibleState(boolean state);

        void continueVisibleState(boolean state);
    }

    interface Presenter {

        void onClickContinue();

        void onClickInfo();

        void onClickRating();

        void onClickNewGame();
    }
}
