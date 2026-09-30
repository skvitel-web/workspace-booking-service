package com.workspace.booking.controller;


import com.workspace.booking.dto.BookingRequestDto;
import com.workspace.booking.entity.Booking;
import com.workspace.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequestDto requestDto){
        Booking booking = bookingService.createBooking(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getAllBooking(@PathVariable Long userId){

        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id){
        Booking cancelledBooking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(cancelledBooking);
    }

}
