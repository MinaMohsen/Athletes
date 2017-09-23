package com.demo.athletes.view;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.athletes.R;
import com.demo.athletes.databinding.ActivityAthleteDetailBinding;
import com.demo.athletes.model.Athlete;
import com.demo.athletes.viewmodel.AthleteDetailViewModel;


public class AthleteDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ATHLETE = "EXTRA_ATHLETE";

    private ActivityAthleteDetailBinding athleteDetailActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        athleteDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_athlete_detail);
        setSupportActionBar(athleteDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, Athlete athlete) {
        Intent intent = new Intent(context, AthleteDetailActivity.class);
        intent.putExtra(EXTRA_ATHLETE, athlete);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void getExtrasFromIntent() {
        Athlete athlete = (Athlete) getIntent().getSerializableExtra(EXTRA_ATHLETE);
        AthleteDetailViewModel athleteDetailViewModel = new AthleteDetailViewModel(athlete);
        athleteDetailActivityBinding.setAthleteDetailViewModel(athleteDetailViewModel);
        setTitle(athlete.getName());
    }


}
