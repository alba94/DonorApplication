//package com.enterprise;
//
//import android.app.Application;
//
//import com.enterprise.dagger.cmp.ApplicationComponent;
//import com.enterprise.dagger.cmp.DaggerApplicationComponent;
//
///**
// * Created by dlika on 6/2/2017.
// */
//
//public class MobileApplication extends Application {
//
//    private static MobileApplication application;
//
//    private static ApplicationComponent applicationComponent;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        application = this;
//        buildComponent();
//    }
//
//    public static ApplicationComponent component() {
//        return applicationComponent;
//    }
//
//    public static void buildComponent() {
//        applicationComponent = DaggerApplicationComponent.Initializer.init(application);
//        applicationComponent.inject(application);
//    }
//
//
//}
