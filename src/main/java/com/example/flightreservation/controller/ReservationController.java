package com.example.flightreservation.controller;

import com.example.flightreservation.dto.ReservationDto;
import com.example.flightreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> bookSeat(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.bookSeat(reservationDto));
    }
}
