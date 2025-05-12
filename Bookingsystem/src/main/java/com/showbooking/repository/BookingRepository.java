package com.showbooking.repository;

import com.showbooking.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingRepository {

    private final Map<String, List<Booking>> userBookings = new HashMap<>();
    private final Map<String, Integer> showBookingCounts = new HashMap<>();

    public List<Booking> getUserBookings(String user) {
        return userBookings.getOrDefault(user, new ArrayList<>());
    }

    public void addBooking(String user, Booking booking) {
        System.out.println("Adding booking: " + booking.getBookingId());
        userBookings.computeIfAbsent(user, k -> new ArrayList<>()).add(booking);
        showBookingCounts.put(booking.getShowName(),
                showBookingCounts.getOrDefault(booking.getShowName(), 0) + booking.getCount());
    }

    public void removeBooking(String user, Booking booking) {
        System.out.println("Removing booking: " + booking.getBookingId());
        List<Booking> bookings = userBookings.get(user);
        if (bookings != null) {
            bookings.remove(booking);
            showBookingCounts.put(booking.getShowName(),
                    showBookingCounts.getOrDefault(booking.getShowName(), 0) - booking.getCount());
        }
    }

}
