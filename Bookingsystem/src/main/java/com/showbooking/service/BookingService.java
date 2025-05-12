package com.showbooking.service;

public interface BookingService {
    String bookTicket(String user, String showName, String time, int count);
    void cancelBooking(String bookingId);
    void showBookings(String user);
    String getTrendingShow();

}
