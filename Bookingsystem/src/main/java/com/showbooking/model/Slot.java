package com.showbooking.model;

import java.util.LinkedList;
import java.util.Queue;

public class Slot {
    private String startTime;
    private int capacity;
    private int booked;
    private Queue<WaitlistEntry> waitlist = new LinkedList<>();

    public Slot(String startTime, int capacity) {
        this.startTime = startTime;
        this.capacity = capacity;
        this.booked = 0;
    }

    public boolean hasCapacity(int count) {
        return booked + count <= capacity;
    }

    public void book(int count) {
        booked += count;
    }

    public void cancel(int count) {
        booked -= count;
    }

    public void addToWaitlist(WaitlistEntry entry) {
        waitlist.add(entry);
    }

    public WaitlistEntry pollWaitlist() {
        return waitlist.poll();
    }

    public String getStartTime() {
        return startTime;
    }

    public int getAvailableCapacity() {
        return capacity - booked;
    }
}
