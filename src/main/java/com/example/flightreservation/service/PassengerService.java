package com.example.flightreservation.service;

import com.example.flightreservation.dto.PassengerDto;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.model.Passenger;
import com.example.flightreservation.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerDto addPassenger(PassengerDto passengerDto) {
        Passenger passenger = Passenger.builder()
                .firstName(passengerDto.getFirstName())
                .lastName(passengerDto.getLastName())
                .email(passengerDto.getEmail())
                .build();

        Passenger savedPassenger = passengerRepository.save(passenger);
        return convertToDto(savedPassenger);
    }

    public PassengerDto getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));
        return convertToDto(passenger);
    }

    public List<PassengerDto> getAllPassengers() {
        return passengerRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PassengerDto updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setEmail(passengerDto.getEmail());

        Passenger updatedPassenger = passengerRepository.save(passenger);
        return convertToDto(updatedPassenger);
    }

    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Passenger not found");
        }
        passengerRepository.deleteById(id);
    }

    private PassengerDto convertToDto(Passenger passenger) {
        return new PassengerDto(passenger.getId(), passenger.getFirstName(), passenger.getLastName(), passenger.getEmail());
    }
}
