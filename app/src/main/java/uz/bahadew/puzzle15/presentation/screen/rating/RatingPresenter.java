package uz.bahadew.puzzle15.presentation.screen.rating;

public class RatingPresenter implements RatingContract.Presenter {
    private final RatingContract.Model model = new RatingModel();
    private final RatingContract.View view;

    RatingPresenter(RatingContract.View view) {
        this.view = view;
        view.init();
    }

    @Override
    public void onClickBack() {
        view.backToMenu();
    }
}
