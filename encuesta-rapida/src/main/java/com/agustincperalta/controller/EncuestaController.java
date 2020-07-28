package com.agustincperalta.controller;

import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agustincperalta.domain.Encuesta;
import com.agustincperalta.repository.EncuestaRepository;

@RestController
public class EncuestaController {

  // Inject en lugar de Autowired para futuras migraciones
  @Inject
  private EncuestaRepository encuestaRepository;

  @GetMapping("/encuestas")
  public ResponseEntity<Iterable<Encuesta>> getAllEncuestas() {
    Iterable<Encuesta> encuestas = encuestaRepository.findAll();
    return new ResponseEntity<>(encuestas, HttpStatus.OK);
  }

}
