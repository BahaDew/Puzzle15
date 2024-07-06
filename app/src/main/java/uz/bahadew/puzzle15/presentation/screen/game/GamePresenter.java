package uz.bahadew.puzzle15.presentation.screen.game;

public class GamePresenter implements GameContract.Presenter {

    private final GameContract.Model model = new GameModel();
    private final GameContract.View view;

    GamePresenter(GameContract.View view) {
        this.view = view;
    }
}
