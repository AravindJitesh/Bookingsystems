package com.showbooking.contoller;

import com.showbooking.service.ShowService;
import com.showbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShowBookingController {

    @Autowired
    private ShowService showService;

    @Autowired
    private BookingService bookingService;

   //only added for structure, testing provided in the main class
}
