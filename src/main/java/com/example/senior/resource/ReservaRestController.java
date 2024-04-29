package com.example.senior.resource;

import com.example.senior.dto.ReservaDTO;
import com.example.senior.dto.ReservaHospedeDTO;
import com.example.senior.service.ReservaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/reserva")
public class ReservaRestController extends BaseRestController {

    @Autowired
    @Lazy
    private ReservaService service;

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @PostMapping("/incluir-reserva")
    public ResponseEntity<ReservaDTO> incluirReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.incluirReserva(reservaDTO));
        } catch (Exception e) {
            logger.info("Não foi possivel realizar a reserva do hotel", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reservaDTO);
        }
    }

    @GetMapping("/consultar-historico/{termo}")
    public ResponseEntity<ReservaHospedeDTO> consultarHistoricoHospede(@PathVariable String termo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.consultarHistoricoHospede(termo));
        } catch (Exception e) {
            logger.info("Não foi possivel consultar o historico do hospede", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/consultar-hospedes/presentes")
    public ResponseEntity<List<ReservaDTO>> consultarHospedesPresentes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.consultarHospedesPresentes());
        } catch (Exception e) {
            logger.info("Não foi possivel consultar todos os hospedes presentes no hotel", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/consultar-hospedes/passados")
    public ResponseEntity<List<ReservaDTO>> consultarHospedesPassados() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.consultarHospedesPassados());
        } catch (Exception e) {
            logger.info("Não foi possivel consultar todos os hospedes que passaram pelo hotel", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
