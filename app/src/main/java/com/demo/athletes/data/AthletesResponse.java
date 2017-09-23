package com.demo.athletes.data;

import com.demo.athletes.model.Athlete;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AthletesResponse {

    @SerializedName("athletes")
    private List<Athlete> athletes;

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }
}
