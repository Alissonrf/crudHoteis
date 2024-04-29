package com.example.senior.resource;

import com.example.senior.dto.HospedeDTO;
import com.example.senior.service.HospedeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hospede")
public class HospedeRestController extends BaseRestController {

    @Autowired
    @Lazy
    private HospedeService service;

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @GetMapping("buscar/{idHospede}")
    public ResponseEntity<HospedeDTO> buscarHospede(@PathVariable Long idHospede) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarHospede(idHospede));
        } catch (Exception e) {
            logger.info(String.format("Hospede com id %s não foi encontrado", idHospede), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<HospedeDTO> incluirHospede(@RequestBody HospedeDTO hospedeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.incluirHospede(hospedeDTO));
        } catch (Exception e) {
            logger.info("Não foi possivel adicionar o novo hospede", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hospedeDTO);
        }
    }

    @PutMapping
    public ResponseEntity<HospedeDTO> atualizarHospede(@RequestBody HospedeDTO hospedeDTO) {
        try {
            HospedeDTO hospedeAtualizado = service.atualizarHospede(hospedeDTO);
            logger.info("Hospede atualizado com sucesso");
            return ResponseEntity.status(HttpStatus.OK).body(hospedeAtualizado);
        } catch (Exception e) {
            logger.info(String.format("Não foi possivel atualizar o hospede com id %s", hospedeDTO.getId()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/remover/{idHospede}")
    public ResponseEntity<Void> removerHospede(@PathVariable Long idHospede) {
        try {
            service.removerHospede(idHospede);
            logger.info("Hospede deletado com sucesso");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.info(String.format("Não foi possivel deletar o hospede com id %s", idHospede));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
