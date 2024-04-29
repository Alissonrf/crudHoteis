package com.example.senior.service;

import com.example.senior.dto.HospedeDTO;
import com.example.senior.entity.HospedeEntity;
import com.example.senior.mapper.HospedeMapper;
import com.example.senior.repository.HospedeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class HospedeService {

    Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    @Lazy
    private HospedeRepository hospedeRepository;

    @Autowired
    @Lazy
    private HospedeMapper mapper;

    public HospedeDTO incluirHospede(HospedeDTO hospedeDTO) {
        return mapper.toDTO(hospedeRepository.save(mapper.toEntity(hospedeDTO)));
    }

    public HospedeDTO buscarHospede(Long idHospede) {
        return mapper.toDTO(hospedeRepository.findById(idHospede)
                .orElseThrow(() -> new MensagemValidacaoException(String.format("Hospede com id %s não encontrado", idHospede))));

    }

    public void removerHospede(Long idHospede) {
        hospedeRepository.deleteById(idHospede);
    }

    public HospedeDTO atualizarHospede(HospedeDTO hospedeDTO) {
        HospedeEntity hospedeEntity = hospedeRepository.findById(hospedeDTO.getId())
                .orElseThrow(() -> new MensagemValidacaoException(String.format("Hospede com id %s não encontrado", hospedeDTO.getId())));
        HospedeEntity hospedeSalvo = hospedeRepository.save(mapper.toEntity(hospedeDTO, hospedeEntity));
        return mapper.toDTO(hospedeSalvo);
    }

}
