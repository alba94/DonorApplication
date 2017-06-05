package com.enterprise.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.enterprise.Services.DonationService;
import com.enterprise.Services.DonationServiceImpl;
import com.enterprise.Services.LoginService;
import com.enterprise.Services.LoginServiceImpl;
import com.enterprise.dagger.ApplicationContext;
import com.enterprise.dagger.DatabaseInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dlika on 6/1/2017.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }


    @Provides
    @Singleton
    DonationService getDonationService() {
        return new DonationServiceImpl();
    }

    @Provides
    @Singleton
    LoginService getLoginService() {
        return new LoginServiceImpl();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "mobileservices.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 1;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return application.getSharedPreferences("mobilepref", Context.MODE_PRIVATE);
    }

}
