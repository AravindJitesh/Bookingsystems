package com.showbooking;
import com.showbooking.model.Genre;
import com.showbooking.model.Show;
import com.showbooking.repository.BookingRepository;
import com.showbooking.service.BookingService;
import com.showbooking.service.BookingServiceImpl;
import com.showbooking.service.ShowService;
import com.showbooking.service.ShowServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class BookingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingsystemApplication.class, args);


		Map<String, Show> showMap = new HashMap<>();
		ShowService showService = new ShowServiceImpl(showMap);
		BookingRepository bookingRepository = new BookingRepository();

		BookingService bookingService = new BookingServiceImpl(bookingRepository, showMap);


		System.out.println("i: registerShow: TMKOC -> Comedy");
		showService.registerShow("TMKOC", Genre.COMEDY);

		System.out.println("i: onboardShowSlots: TMKOC 9:00-11:00");
		showService.onboardSlots("TMKOC", List.of("9:00-11:00 3"));

		System.out.println("i: onboardShowSlots: TMKOC 9:00-10:00 3, 12:00-13:00 2, 15:00-16:00 5");
		showService.onboardSlots("TMKOC", List.of("9:00-10:00 3", "12:00-13:00 2", "15:00-16:00 5"));

		System.out.println("i: registerShow: The Sonu Nigam Live Event -> Singing");
		showService.registerShow("The Sonu Nigam Live Event", Genre.SINGING);

		System.out.println("i: onboardShowSlots: The Sonu Nigam Live Event 10:00-11:00 3, 13:00-14:00 2, 17:00-18:00 1");
		showService.onboardSlots("The Sonu Nigam Live Event", List.of("10:00-11:00 3", "13:00-14:00 2", "17:00-18:00 1"));

		System.out.println("i: showAvailByGenre: Comedy");
		showService.showByGenre(Genre.COMEDY);

		System.out.println("i: bookTicket: (UserA, TMKOC, 12:00, 2)");
		String bookingId = bookingService.bookTicket("UserA", "TMKOC", "12:00", 2);
		System.out.println("Booking id: " + bookingId);

		System.out.println("i: showAvailByGenre: Comedy");
		showService.showByGenre(Genre.COMEDY);

		System.out.println("i: cancelBookingId: " + bookingId);
		bookingService.cancelBooking(bookingId);

		System.out.println("i: showAvailByGenre: Comedy");
		showService.showByGenre(Genre.COMEDY);

		System.out.println("i: bookTicket: (UserB, TMKOC, 12:00, 1)");
		bookingService.bookTicket("UserB", "TMKOC", "12:00", 1);

		System.out.println("i: registerShow: The Arijit Singh Live Event -> Singing");
		showService.registerShow("The Arijit Singh Live Event", Genre.SINGING);

		System.out.println("i: onboardShowSlots: The Arijit Singh Live Event 11:00-12:00 3, 14:00-15:00 2");
		showService.onboardSlots("The Arijit Singh Live Event", List.of("11:00-12:00 3", "14:00-15:00 2"));

		System.out.println("i: showAvailByGenre: Singing");
		showService.showByGenre(Genre.SINGING);


		System.out.println("i: showBookings: UserB");
		bookingService.showBookings("UserB");




		//bonus feature
		System.out.println("Trending Show: " + bookingService.getTrendingShow());
	}

}


