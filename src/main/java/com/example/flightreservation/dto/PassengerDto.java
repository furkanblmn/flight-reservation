package com.example.flightreservation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
