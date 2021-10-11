package com.example.inductionapp;

public class ActivitiesModel {

    String activityName;

    public ActivitiesModel(){

    }

    public ActivitiesModel(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
