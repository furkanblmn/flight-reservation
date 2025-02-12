package com.example.flightreservation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
    private Long id;
    private String seatNumber;
    private double price;
    private Long flightId;
    private boolean available;
}
