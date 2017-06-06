package com.enterprise.dagger;

import com.enterprise.MobileApplication;
import com.enterprise.dagger.cmp.ApplicationComponent;
import com.enterprise.dagger.cmp.DaggerApplicationComponent;
import com.enterprise.dagger.module.ApplicationModule;

/**
 * Created by dlika on 6/5/2017.
 */

public class Injector {

    private static ApplicationComponent applicationComponent;

    public static void initialize(MobileApplication application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    public static ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    private Injector() {
    }
}
