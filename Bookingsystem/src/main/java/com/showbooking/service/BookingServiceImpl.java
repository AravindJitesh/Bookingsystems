package com.showbooking.service;

import com.showbooking.model.*;
import com.showbooking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final Map<String, Booking> bookingMap = new HashMap<>();
    private final Map<String, Show> shows;
    private final Map<String, Integer> bookingCountMap = new HashMap<>();
    private String trendingShow = null;

    public BookingServiceImpl(BookingRepository repository, Map<String, Show> shows) {
        this.repository = repository;
        this.shows = shows;
    }

    @Override
    public String bookTicket(String user, String showName, String time, int count) {
        System.out.println("[BOOKING] Trying to book for " + user + " -> " + showName + " at " + time);

        Show show = shows.get(showName);
        if (show == null) {
            System.out.println("Show not found.");
            return null;
        }

        Slot slot = null;
        String fullSlotTime = null;
        for (Map.Entry<String, Slot> entry : show.getSlots().entrySet()) {
            if (entry.getKey().startsWith(time)) {
                slot = entry.getValue();
                fullSlotTime = entry.getKey();
                break;
            }
        }

        if (slot == null) {
            System.out.println("Time slot not found.");
            return null;
        }


        if (hasBookingAtTime(user, time)) {
            System.out.println("User already has a booking at this time.");
            return null;
        }

        if (slot.hasCapacity(count)) {
            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(bookingId, user, showName, fullSlotTime, count);
            slot.book(count);
            bookingMap.put(bookingId, booking);
            repository.addBooking(user, booking);
            incrementBookingCount(showName, count);
            System.out.println("Booked. Booking id: " + bookingId);
            return bookingId;
        } else {
            slot.addToWaitlist(new WaitlistEntry(user, count));
            System.out.println("Added to waitlist.");
            return null;
        }
    }
    @Override
    public void cancelBooking(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        Show show = shows.get(booking.getShowName());
        if (show == null) {
            System.out.println("Show not found for booking.");
            return;
        }

        Slot slot = show.getSlots().get(booking.getStartTime());
        if (slot == null) {
            System.out.println("Slot not found for booking.");
            return;
        }

        slot.cancel(booking.getCount());
        repository.removeBooking(booking.getUserName(), booking);
        bookingMap.remove(bookingId);
        System.out.println("Booking Canceled");

        WaitlistEntry next = slot.pollWaitlist();
        if (next != null && slot.hasCapacity(next.getCount())) {
            bookTicket(next.getUserName(), booking.getShowName(), booking.getStartTime(), next.getCount());
        }
    }

    @Override
    public void showBookings(String user) {
        List<Booking> bookings = repository.getUserBookings(user);
        bookings.forEach(b -> System.out.println(b.getShowName() + ": (" + b.getStartTime() + ") " + b.getCount()));
    }

    public String getTrendingShow() {
        return trendingShow;
    }

    private void incrementBookingCount(String showName, int count) {
        int newCount = bookingCountMap.getOrDefault(showName, 0) + count;
        bookingCountMap.put(showName, newCount);

        if (trendingShow == null || newCount > bookingCountMap.getOrDefault(trendingShow, 0)) {
            trendingShow = showName;
        }
    }

    private boolean hasBookingAtTime(String user, String startTime) {
        List<Booking> userBookings = repository.getUserBookings(user);
        for (Booking booking : userBookings) {
            if (booking.getStartTime().startsWith(startTime)) {
                return true;
            }
        }
        return false;
    }


}
