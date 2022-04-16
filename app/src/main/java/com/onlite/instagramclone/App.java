package com.onlite.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bjAsMq9QiBhO4QgIl7VlG6wdmMovkfgWrbAiXYcY")
                // if defined
                .clientKey("msZAP9aa93AF8fhA2bLGDG42H1h4rXA17nNArGmd")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
