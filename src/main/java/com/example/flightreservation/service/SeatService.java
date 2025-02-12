package com.example.flightreservation.service;

import com.example.flightreservation.dto.SeatDto;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final FlightRepository flightRepository;

    public SeatDto addSeat(SeatDto seatDto) {
        Flight flight = flightRepository.findById(seatDto.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight with ID " + seatDto.getFlightId() + " not found"));

        Seat seat = new Seat();
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setPrice(seatDto.getPrice());
        seat.setFlight(flight);
        seat.setAvailable(true);

        Seat savedSeat = seatRepository.save(seat);
        return convertToDto(savedSeat);
    }

    public SeatDto updateSeat(Long id, SeatDto seatDto) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat with ID " + id + " not found"));

        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setPrice(seatDto.getPrice());

        Seat updatedSeat = seatRepository.save(seat);
        return convertToDto(updatedSeat);
    }

    public void deleteSeat(Long id) {
        if (!seatRepository.existsById(id)) {
            throw new ResourceNotFoundException("Seat with ID " + id + " not found");
        }
        seatRepository.deleteById(id);
    }

    public List<SeatDto> getSeatsByFlight(Long flightId) {
        return seatRepository.findByFlightId(flightId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SeatDto convertToDto(Seat seat) {
        return new SeatDto(seat.getId(), seat.getSeatNumber(), seat.getPrice(), seat.getFlight().getId(), seat.isAvailable());
    }
}
