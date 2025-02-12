package com.example.flightreservation.controller;

import com.example.flightreservation.dto.SeatDto;
import com.example.flightreservation.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatDto> addSeat(@RequestBody SeatDto seatDto) {
        return ResponseEntity.ok(seatService.addSeat(seatDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable Long id, @RequestBody SeatDto seatDto) {
        return ResponseEntity.ok(seatService.updateSeat(id, seatDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<List<SeatDto>> getSeatsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(seatService.getSeatsByFlight(flightId));
    }
}
