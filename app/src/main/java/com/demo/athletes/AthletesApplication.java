package com.demo.athletes;

import android.app.Application;
import android.content.Context;

import com.demo.athletes.data.AthletesFactory;
import com.demo.athletes.data.AthletesService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class AthletesApplication extends Application {

    private AthletesService athletesService;
    private Scheduler scheduler;

    private static AthletesApplication get(Context context) {
        return (AthletesApplication) context.getApplicationContext();
    }

    public static AthletesApplication create(Context context) {
        return AthletesApplication.get(context);
    }

    public AthletesService getAthletesService() {
        if (athletesService == null) {
            athletesService = AthletesFactory.create();
        }
        return athletesService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void setAthletesService(AthletesService athletesService) {
        this.athletesService = athletesService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
