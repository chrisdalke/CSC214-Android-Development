////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model.Runs;

import csc214.project03.Model.DateManager;

////////////////////////////////////////
// Run Model
////////////////////////////////////////

public class Run {
    public int id;
    private float distance;
    private String duration;
    private int day;
    private int week;
    private int month;
    private int year;

    public String extraHasImage;
    public String extraHasWeather;
    public String extraHasLocation;
    public String extraImage;
    public String extraWeatherData;
    public float lat;
    public float lon;

    public String getExtraHasImage() {
        return extraHasImage;
    }

    public void setExtraHasImage(String extraHasImage) {
        this.extraHasImage = extraHasImage;
    }

    public String getExtraHasWeather() {
        return extraHasWeather;
    }

    public void setExtraHasWeather(String extraHasWeather) {
        this.extraHasWeather = extraHasWeather;
    }

    public String getExtraHasLocation() {
        return extraHasLocation;
    }

    public void setExtraHasLocation(String extraHasLocation) {
        this.extraHasLocation = extraHasLocation;
    }

    public String getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(String extraImage) {
        this.extraImage = extraImage;
    }

    public String getExtraWeatherData() {
        return extraWeatherData;
    }

    public void setExtraWeatherData(String extraWeatherData) {
        this.extraWeatherData = extraWeatherData;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public Run(){
        day = DateManager.getDay();
        week = DateManager.getWeek();
        month = DateManager.getMonth();
        year = DateManager.getYear();
        extraHasImage = "false";
        extraHasWeather = "false";
        extraHasLocation = "false";
        duration="00:00:00";
        distance = 0;
    }

    public String calculatePace(){
        //Calculate pace based on the distance and the time
        String[] splitTimeString = duration.split(":");
        int hours = Integer.parseInt(splitTimeString[0]);
        int minutes = Integer.parseInt(splitTimeString[1]);
        int seconds = Integer.parseInt(splitTimeString[2]);

        int secondsOnly = seconds + (minutes * 60) + (hours * 3600);
        int secondsOnlyPace = Math.round((float)secondsOnly / distance);

        int rHours = 0;
        int rMinutes = 0;
        int rSeconds = 0;

        while (secondsOnlyPace >= 3600){
            secondsOnlyPace -= 3600;
            rHours += 1;
        }
        while (secondsOnlyPace >= 60){
            secondsOnlyPace -= 60;
            rMinutes += 1;
        }
        while (secondsOnlyPace > 0){
            secondsOnlyPace -= 1;
            rSeconds += 1;
        }

        return String.format("%02d", rHours)+":"+String.format("%02d", rMinutes)+":"+String.format("%02d", rSeconds);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Run run = (Run) o;

        if (id != run.id) return false;
        if (Float.compare(run.distance, distance) != 0) return false;
        if (day != run.day) return false;
        if (week != run.week) return false;
        if (month != run.month) return false;
        if (year != run.year) return false;
        return duration != null ? duration.equals(run.duration) : run.duration == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (distance != +0.0f ? Float.floatToIntBits(distance) : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + day;
        result = 31 * result + week;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Run{" +
                "id=" + id +
                ", distance=" + distance +
                ", duration='" + duration + '\'' +
                ", day=" + day +
                ", week=" + week +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////