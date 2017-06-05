package com.enterprise.dagger.cmp;

import android.app.Application;
import android.content.Context;

import com.enterprise.DataBase.DBHelper;
import com.enterprise.MobileApplication;
import com.enterprise.Session.SessionManager;
import com.enterprise.dagger.ApplicationContext;
import com.enterprise.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dlika on 6/2/2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface ApplicationComponent {

    void inject(MobileApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    SessionManager getSessionManager();

    DBHelper getDBHelper();

}
