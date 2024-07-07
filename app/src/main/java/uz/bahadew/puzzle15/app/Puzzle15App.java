package uz.bahadew.puzzle15.app;

import android.app.Application;
import android.content.Context;

import uz.bahadew.puzzle15.data.MyShared;
import uz.bahadew.puzzle15.domain.RepositoryImpl;

public class Puzzle15App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // ketma-ketlik muhim MyShared RepositoryImpl dan oldin init bo'lishi shart
        MyShared.init(this.getSharedPreferences("BAHA_DEW_PUZZLE15", Context.MODE_PRIVATE));
        RepositoryImpl.init();
    }
}
