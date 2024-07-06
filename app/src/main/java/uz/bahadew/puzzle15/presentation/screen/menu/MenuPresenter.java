package uz.bahadew.puzzle15.presentation.screen.menu;

public class MenuPresenter implements MenuContract.Presenter {

    private final MenuContract.Model model = new MenuModel();
    private final MenuContract.View view;

    public MenuPresenter(MenuContract.View view) {
        this.view = view;
        view.init();
        //
        view.continueVisibleState(true);
        view.ratingVisibleState(true);
    }

    @Override
    public void onClickContinue() {
        view.goContinue();
    }

    @Override
    public void onClickInfo() {
        view.goInfo();
    }

    @Override
    public void onClickRating() {
        view.goRating();
    }

    @Override
    public void onClickNewGame() {
        view.goNewGame();
    }
}
