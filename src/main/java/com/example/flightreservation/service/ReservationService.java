package com.example.flightreservation.service;

import com.example.flightreservation.dto.ReservationDto;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Passenger;
import com.example.flightreservation.model.Reservation;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.PassengerRepository;
import com.example.flightreservation.repository.ReservationRepository;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final PassengerRepository passengerRepository;

    @Transactional
    public ReservationDto bookSeat(ReservationDto reservationDto) {
        Flight flight = flightRepository.findById(reservationDto.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        Seat seat = seatRepository.findById(reservationDto.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

        if (!seat.isAvailable()) {
            throw new RuntimeException("Seat is already booked");
        }

        Passenger passenger = passengerRepository.findById(reservationDto.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        seat.setAvailable(false);
        seatRepository.save(seat);

        Reservation reservation = Reservation.builder()
                .flight(flight)
                .seat(seat)
                .passenger(passenger)
                .confirmed(true)
                .build();

        reservationRepository.save(reservation);

        return new ReservationDto(
                reservation.getFlight().getId(),
                reservation.getSeat().getId(),
                reservation.getPassenger().getId(),
                reservation.isConfirmed()
        );
    }
}
