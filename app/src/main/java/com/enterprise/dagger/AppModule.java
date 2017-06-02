package com.enterprise.dagger;

import android.app.Application;
import android.content.Context;

import com.enterprise.Session.SessionManager;
import com.enterprise.Session.SessionManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dlika on 6/1/2017.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    SessionManager getSessionManager() {
        return new SessionManagerImpl();
    }

}
