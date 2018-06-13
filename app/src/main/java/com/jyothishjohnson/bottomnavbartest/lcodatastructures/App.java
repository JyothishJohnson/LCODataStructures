package com.jyothishjohnson.bottomnavbartest.lcodatastructures;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by jyothish on 2/9/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("Quicksand-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
