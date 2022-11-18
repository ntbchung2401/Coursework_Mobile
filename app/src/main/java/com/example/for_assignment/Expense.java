package com.example.for_assignment;

public class Expense {
    private int id;
    private String typeOfExense;
    private double amount;
    private String date;
    private Trip trip_id;

    public Expense() {
    }
    public Expense(int id, String typeOfExense, double amount, String date, Trip trip_id) {
        this.id = id;
        this.typeOfExense = typeOfExense;
        this.amount = amount;
        this.date = date;
        this.trip_id = trip_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfExense() {
        return typeOfExense;
    }

    public void setTypeOfExense(String typeOfExense) {
        this.typeOfExense = typeOfExense;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Trip getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Trip trip_id) {
        this.trip_id = trip_id;
    }
}
