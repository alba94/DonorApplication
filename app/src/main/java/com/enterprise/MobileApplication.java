package com.enterprise;

import android.app.Application;
import android.content.Context;

import com.enterprise.Session.SessionManager;
import com.enterprise.dagger.cmp.ApplicationComponent;
import com.enterprise.dagger.cmp.DaggerApplicationComponent;
import com.enterprise.dagger.module.AppModule;

import javax.inject.Inject;

/**
 * Created by dlika on 6/2/2017.
 */

public class MobileApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    SessionManager sessionManager;

    public static MobileApplication get(Context context) {
        return (MobileApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
