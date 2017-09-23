package com.demo.athletes.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Athlete implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String athleteImage;

    @SerializedName("brief")
    private String brief;

    public Athlete() {
    }

    public Athlete(String name, String athleteImage, String brief) {
        this.name = name;
        this.athleteImage = athleteImage;
        this.brief = brief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAthleteImage() {
        return athleteImage;
    }

    public void setAthleteImage(String athleteImage) {
        this.athleteImage = athleteImage;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
