package com.rachid_abbad.micropythonapp.models;

public class DataModel {

    private Double temp,humidity;
    private boolean alertStat,ledStat;

    public DataModel(Double temp, Double humidity, boolean alertStat, boolean ledStat) {
        this.temp = temp;
        this.humidity = humidity;
        this.alertStat = alertStat;
        this.ledStat = ledStat;
    }

    public DataModel() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public boolean isAlertStat() {
        return alertStat;
    }

    public void setAlertStat(boolean alertStat) {
        this.alertStat = alertStat;
    }

    public boolean isLedStat() {
        return ledStat;
    }

    public void setLedStat(boolean ledStat) {
        this.ledStat = ledStat;
    }
}
