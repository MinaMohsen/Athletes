package com.demo.athletes.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.demo.athletes.AthletesApplication;
import com.demo.athletes.R;
import com.demo.athletes.data.AthletesFactory;
import com.demo.athletes.data.AthletesResponse;
import com.demo.athletes.data.AthletesService;
import com.demo.athletes.model.Athlete;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AthletesViewModel extends Observable {

    public ObservableInt athletesProgress;
    public ObservableInt athletesRecycler;
    public ObservableInt athletesLabel;
    public ObservableField<String> messageLabel;

    private List<Athlete> athletesList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AthletesViewModel(@NonNull Context context) {
        this.context = context;
        this.athletesList = new ArrayList<>();
        athletesProgress = new ObservableInt(View.GONE);
        athletesRecycler = new ObservableInt(View.GONE);
        athletesLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_athlete));
    }

    public void onClickFabLoad(View view) {

        initializeViews();
        fetchAthletesList();
    }

    private void initializeViews() {
        athletesLabel.set(View.GONE);
        athletesRecycler.set(View.GONE);
        athletesProgress.set(View.VISIBLE);
    }

    private void fetchAthletesList() {
        AthletesApplication athletesApplication = AthletesApplication.create(context);
        AthletesService athletesService = athletesApplication.getAthletesService();

        Disposable disposable = athletesService.fetchAthletes(AthletesFactory.ATHLETES_URL)
                .subscribeOn(athletesApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AthletesResponse>() {
                    @Override
                    public void accept(AthletesResponse athletesResponse) throws Exception {
                        changePeopleDataSet(athletesResponse.getAthletes());
                        athletesProgress.set(View.GONE);
                        athletesLabel.set(View.GONE);
                        athletesRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("Test",throwable.getMessage() + " " + throwable.getCause());
                        messageLabel.set(context.getString(R.string.error_loading_athletes));
                        athletesProgress.set(View.GONE);
                        athletesLabel.set(View.VISIBLE);
                        athletesRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changePeopleDataSet(List<Athlete> peoples) {
        athletesList.clear();
        athletesList.addAll(peoples);
        setChanged();
        notifyObservers();
    }

    public List<Athlete> getAthletesList() {
        return athletesList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }




}
