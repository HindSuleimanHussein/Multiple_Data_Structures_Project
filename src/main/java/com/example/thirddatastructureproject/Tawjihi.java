package com.example.thirddatastructureproject;

public class Tawjihi implements Comparable<Tawjihi>{
    private int seatNumber;
    private String branch;
    private double average;

    public Tawjihi(){

    }

    public Tawjihi(int seatNumber, String branch, double average) {
        this.seatNumber = seatNumber;
        this.branch = branch;
        this.average = average;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "Tawjihi{" +
                "seatNumber=" + seatNumber +
                ", branch='" + branch + '\'' +
                ", average=" + average +
                '}';
    }

    @Override
    public int compareTo(Tawjihi o) {
        return Double.compare(this.getAverage(), o.getAverage());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        else if ((o instanceof Tawjihi))
            return this.seatNumber==((Tawjihi)o).seatNumber;

        else
            return super.equals(o);
    }
}