package com.example.senior.dto;

import com.example.senior.entity.HospedeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservaDTO {

    private Long id;
    private HospedeEntity hospede;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private double valorReserva;
    private boolean adicionalVeiculo;

}
