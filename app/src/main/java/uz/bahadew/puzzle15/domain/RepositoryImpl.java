package uz.bahadew.puzzle15.domain;

import uz.bahadew.puzzle15.data.MyShared;

public class RepositoryImpl implements Repository {
    private final MyShared myShared = MyShared.getInstance();

    private RepositoryImpl() {

    }

    private static RepositoryImpl instance;

    public static void init() {
        if(instance == null) {
            instance = new RepositoryImpl();
        }
    }

    public static RepositoryImpl getInstance() {
        return instance;
    }
}
