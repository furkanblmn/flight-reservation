package com.example.flightreservation.service;

import com.example.flightreservation.dto.FlightDto;
import com.example.flightreservation.dto.SeatDto;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.model.Flight;
import com.example.flightreservation.model.Seat;
import com.example.flightreservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightDto addFlight(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setName(flightDto.getName());
        flight.setDescription(flightDto.getDescription());

        Flight savedFlight = flightRepository.save(flight);
        return convertToDto(savedFlight);
    }

    public FlightDto updateFlight(Long id, FlightDto flightDto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        flight.setName(flightDto.getName());
        flight.setDescription(flightDto.getDescription());
        flight.setPrice(flightDto.getPrice()); 

        Flight updatedFlight = flightRepository.save(flight);
        return convertToDto(updatedFlight);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResourceNotFoundException("Flight with ID " + id + " not found");
        }
        flightRepository.deleteById(id);
    }

     public FlightDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        return convertToDto(flight);
    }

   
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FlightDto convertToDto(Flight flight) {
        List<SeatDto> seatDtos = flight.getSeats().stream()
                .map(this::convertSeatToDto)
                .collect(Collectors.toList());

        return FlightDto.builder()
                .id(flight.getId())
                .name(flight.getName())
                .description(flight.getDescription())
                .price(flight.getPrice())
                .seats(seatDtos) 
                .build();
    }

    private SeatDto convertSeatToDto(Seat seat) {
        return SeatDto.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .price(seat.getPrice())
                .flightId(seat.getFlight().getId())
                .available(seat.isAvailable())
                .build();
    }
}
