//package com.enterprise.dagger.module;
//
//import android.app.Application;
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.enterprise.session.SessionManager;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by dlika on 6/1/2017.
// */
//
//@Module
//public class ApplicationModule {
//
//    private final Application application;
//
//    public ApplicationModule(Application application) {
//        this.application = application;
//    }
//
//    @Provides
//    Application providesApplication() {
//        return application;
//    }
//
//    @Provides
//    SessionManager getSessionManager() {
//        return new SessionManager(application);
//    }
//
//
//    @Provides
//    SharedPreferences provideSharedPrefs() {
//        return application.getSharedPreferences("mobilepref", Context.MODE_PRIVATE);
//    }
//
//}
