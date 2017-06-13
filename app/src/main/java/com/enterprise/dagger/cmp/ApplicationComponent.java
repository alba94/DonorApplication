//package com.enterprise.dagger.cmp;
//
//
//import android.app.Application;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//
//import com.enterprise.MobileApplication;
//import com.enterprise.dagger.module.ApplicationModule;
//
//import javax.inject.Singleton;
//
//import dagger.Component;
//
///**
// * Created by dlika on 6/2/2017.
// */
//
//@Singleton
//@Component(modules = ApplicationModule.class)
//public interface ApplicationComponent {
//
//    void inject(AppCompatActivity activity);
//
//    void inject(Fragment fragment);
//
//    void inject(Application application);
//
//    final class Initializer {
//        private Initializer() {
//        }
//
//        public static ApplicationComponent init(MobileApplication app) {
//            return DaggerApplicationComponent.builder().
//                    applicationModule(new ApplicationModule(app)).build();
//        }
//    }
//}
