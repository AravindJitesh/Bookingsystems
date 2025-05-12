package com.showbooking.model;

public class Booking {
    private String bookingId;
    private String userName;
    private String showName;
    private String startTime;
    private int count;

    public Booking(String bookingId, String userName, String showName, String startTime, int count) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.showName = showName;
        this.startTime = startTime;
        this.count = count;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public String getShowName() {
        return showName;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getCount() {
        return count;
    }
}
