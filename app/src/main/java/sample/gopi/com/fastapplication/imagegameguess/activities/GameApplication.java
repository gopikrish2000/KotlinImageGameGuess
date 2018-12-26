package sample.gopi.com.fastapplication.imagegameguess.activities;

import android.app.Application;

public class GameApplication extends Application {

    private static GameApplication gameApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        gameApplication = this;
    }

    public static GameApplication getAppContext() {
        return gameApplication;
    }
}
