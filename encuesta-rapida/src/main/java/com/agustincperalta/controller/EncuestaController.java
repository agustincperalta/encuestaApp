package com.agustincperalta.controller;

import java.net.URI;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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


  @PostMapping("/encuestas")
  public HttpHeaders createEncuesta(@RequestBody Encuesta encuesta) {
    encuesta = encuestaRepository.save(encuesta);
    HttpHeaders response = new HttpHeaders();
    URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(encuesta.getId()).toUri();

    response.setLocation(locationUri);
    return response;
  }

  @GetMapping("/encuestas/{encuestaId}")
  public ResponseEntity<Encuesta> getEncuesta(@PathVariable Long encuestaId) {
    Optional<Encuesta> encuesta = encuestaRepository.findById(encuestaId);
    return new ResponseEntity<>(encuesta.get(), HttpStatus.OK);
  }

  @PutMapping("/encuestas/{encuestaId}")
  public ResponseEntity<Encuesta> updateEncuesta(@RequestBody Encuesta encuesta,
      @PathVariable Long encuestaId) {

    Encuesta encuestaActualizada = encuestaRepository.save(encuesta);
    return new ResponseEntity<Encuesta>(HttpStatus.OK);

  }

  @DeleteMapping("/encuestas/{encuestaId}")
  public ResponseEntity deleteEncuesta(@PathVariable Long encuestaId) {
    encuestaRepository.deleteById(encuestaId);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
