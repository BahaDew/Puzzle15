package uz.bahadew.puzzle15.data;

import android.content.SharedPreferences;

public class MyShared {

    private static MyShared instance;
    private final SharedPreferences sharedP;

    private MyShared(SharedPreferences sharedP) {
        this.sharedP = sharedP;
    }

    public static void init(SharedPreferences sharedP) {
        if (instance == null) {
            instance = new MyShared(sharedP);
        }
    }

    public static MyShared getInstance() {
        return instance;
    }

    public void putBoolean(String key, boolean value) {
        sharedP.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedP.getBoolean(key, defValue);
    }
}
