package com.example.senior.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservaHospedeDTO {

    private HospedeDTO hospede;
    private double valorTotalGasto;
    private double valorUltimaReserva;

}
