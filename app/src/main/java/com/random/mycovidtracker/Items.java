package com.random.mycovidtracker;

public class Items {

    private final String loc;
    private final String cases;

    public Items (String loc, String cases) {
        this.loc = loc;
        this.cases = cases;
    }

    public String getLoc () {
        return loc;
    }

    public String getCases () {
        return cases;
    }
}
