package com.example.aswincsd214lab2;

public class Players {

    private int id;
    private String name;
    private String sport;
    private int rank;

    public Players(int id, String name, String sport, int rank) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
