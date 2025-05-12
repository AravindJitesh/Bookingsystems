package com.showbooking.model;

public class WaitlistEntry {
    private String userName;
    private int count;

    public WaitlistEntry(String userName, int count) {
        this.userName = userName;
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public int getCount() {
        return count;
    }
}
