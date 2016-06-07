package com.facilitydoor.app.facilitydoor;

import android.app.Application;
import android.content.Context;

/**
 * Created by root on 3/6/16.
 */






    public class ApplicationWrapper extends Application {
        private static ApplicationWrapper applicationWrapper;

        @Override
        public void onCreate() {
            super.onCreate();
            applicationWrapper = this;
            super.onCreate();
        }

        public static ApplicationWrapper getApplicationWrapper() {
            return applicationWrapper;
        }

        public static Context getAppContext() {

            return applicationWrapper.getApplicationContext();

        }

    }



