package com.demo.athletes.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.athletes.R;
import com.demo.athletes.databinding.ActivityAthletesBinding;
import com.demo.athletes.viewmodel.AthletesViewModel;

import java.util.Observable;
import java.util.Observer;

public class AthletesActivity extends AppCompatActivity implements Observer {

    private AthletesViewModel athletesViewModel;
    private ActivityAthletesBinding athletesActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

        setupAthletesListView(athletesActivityBinding.athletesListView);

        setupObserver(athletesViewModel);
    }

    private void initDataBinding() {
        athletesActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_athletes);
        athletesViewModel = new AthletesViewModel(this);
        athletesActivityBinding.setAthletesViewModel(athletesViewModel);
    }

    private void setupAthletesListView(RecyclerView athletesList) {
        AthletesAdapter adapter = new AthletesAdapter();
        athletesList.setAdapter(adapter);
        athletesList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        athletesViewModel.reset();
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof AthletesViewModel) {
            AthletesAdapter athletesAdapter = (AthletesAdapter) athletesActivityBinding.athletesListView.getAdapter();
            AthletesViewModel athletesViewModel = (AthletesViewModel) observable;
            athletesAdapter.setAthletesList(athletesViewModel.getAthletesList());
        }
    }
}
