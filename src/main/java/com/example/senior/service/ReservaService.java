package com.example.senior.service;

import com.example.senior.dto.ReservaDTO;
import com.example.senior.dto.ReservaHospedeDTO;
import com.example.senior.entity.HospedeEntity;
import com.example.senior.entity.ReservaEntity;
import com.example.senior.mapper.HospedeMapper;
import com.example.senior.mapper.ReservaMapper;
import com.example.senior.repository.HospedeRepository;
import com.example.senior.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservaService {

    private static final double VALOR_DIARA_SEMANA = 120.0;
    private static final double VALOR_DIARA_FIM_DE_SEMANA = 150.0;
    private static final double VALOR_ESTACIONAMENTO_SEMANA = 15.0;
    private static final double VALOR_ESTACIONAMENTO_FIM_DE_SEMANA = 20.0;

    @Autowired
    @Lazy
    private ReservaRepository reservaRepository;

    @Autowired
    @Lazy
    private HospedeService hospedeService;

    @Autowired
    @Lazy
    HospedeRepository hospedeRepository;

    @Autowired
    @Lazy
    private ReservaMapper reservaMapper;

    @Autowired
    @Lazy
    private HospedeMapper hospedeMapper;

    public ReservaDTO incluirReserva(ReservaDTO reservaDTO) {
        ReservaEntity reservaEntity = reservaMapper.toEntity(reservaDTO);
        HospedeEntity hospedeEntity = hospedeMapper.toEntity(hospedeService.buscarHospede(reservaDTO.getHospede().getId()));
        reservaEntity.setHospede(hospedeEntity);

        calcularValorDiarias(reservaEntity);
        return reservaMapper.toDTO(reservaRepository.save(reservaEntity));
    }

    private static void calcularValorDiarias(ReservaEntity reservaEntity) {
        LocalDateTime dataCheckin = reservaEntity.getDataEntrada();
        LocalDateTime dataCheckout = reservaEntity.getDataSaida();

        if (!dataCheckout.toLocalTime().isAfter(LocalTime.of(16, 30))) {
            dataCheckout = dataCheckout.minusDays(1);
        }

        double valorTotal = 0.0;
        while (dataCheckin.isBefore(dataCheckout)) {
            DayOfWeek diaSemana = dataCheckin.getDayOfWeek();
            if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
                valorTotal += reservaEntity.isAdicionalVeiculo() ? (VALOR_DIARA_FIM_DE_SEMANA + VALOR_ESTACIONAMENTO_FIM_DE_SEMANA) : VALOR_DIARA_FIM_DE_SEMANA;
            } else {
                valorTotal += reservaEntity.isAdicionalVeiculo() ? (VALOR_DIARA_SEMANA + VALOR_ESTACIONAMENTO_SEMANA) : VALOR_DIARA_SEMANA;
            }
            dataCheckin = dataCheckin.plusDays(1);
        }

        reservaEntity.setValorReserva(valorTotal);
    }


    public List<ReservaDTO> consultarHospedesPresentes() {
        List<ReservaEntity> reservas = reservaRepository.findAll();

        reservas = reservas.stream().filter(reserva -> reserva.getDataEntrada().isBefore(LocalDateTime.now()) && reserva.getDataSaida().isAfter(LocalDateTime.now())).toList();
        return reservaMapper.toDTO(reservas);
    }

    public List<ReservaDTO> consultarHospedesPassados() {
        List<ReservaEntity> reservas = reservaRepository.findAll();

        reservas = reservas.stream().filter(reserva -> reserva.getDataSaida().isBefore(LocalDateTime.now())).toList();
        return reservaMapper.toDTO(reservas);
    }

    public ReservaHospedeDTO consultarHistoricoHospede(String termo) {
        HospedeEntity hospedeCorresponde = filtrarHospedePorTermo(termo);
        List<ReservaEntity> reservas = reservaRepository.findAll();

        reservas = reservas.stream().filter(reserva -> reserva.getHospede().getId().equals(hospedeCorresponde.getId())).toList();
        reservas = reservas.stream().sorted(Comparator.comparing(ReservaEntity::getDataSaida)).toList();

        double valorTotalGasto = 0.0;
        for (ReservaEntity reserva : reservas) {
            valorTotalGasto += reserva.getValorReserva();
        }

        ReservaHospedeDTO reservaHospedeDTO = new ReservaHospedeDTO();
        reservaHospedeDTO.setHospede(hospedeMapper.toDTO(hospedeCorresponde));
        reservaHospedeDTO.setValorUltimaReserva(reservas.get(0).getValorReserva());
        reservaHospedeDTO.setValorTotalGasto(valorTotalGasto);

        return reservaHospedeDTO;
    }

    private HospedeEntity filtrarHospedePorTermo(String termo) {
        List<HospedeEntity> hospedes = hospedeRepository.findAll();

        return hospedes.stream().filter(hospede -> hospede.getNome().toUpperCase().contains(termo.toUpperCase()) ||
                        hospede.getDocumento().contains(termo) ||
                        hospede.getTelefone().contains(termo))
                .findFirst().orElseThrow(() -> new MensagemValidacaoException("Nenhum hospede encontrado."));
    }
}
