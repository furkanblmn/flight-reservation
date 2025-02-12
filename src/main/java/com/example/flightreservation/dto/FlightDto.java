package com.example.flightreservation.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<SeatDto> seats;
}
