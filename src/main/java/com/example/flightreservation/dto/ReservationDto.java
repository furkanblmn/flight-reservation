package com.example.flightreservation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private Long flightId;
    private Long seatId;
    private Long passengerId;
    private boolean confirmed;
}
