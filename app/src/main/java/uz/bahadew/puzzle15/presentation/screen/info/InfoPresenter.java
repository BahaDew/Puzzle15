package uz.bahadew.puzzle15.presentation.screen.info;

public class InfoPresenter implements InfoContract.Presenter {
    private final InfoContract.Model model = new InfoModel();
    private final InfoContract.View view;

    InfoPresenter(InfoContract.View view) {
        this.view = view;
    }
}
